package ba.unsa.etf.pnwt.microservice.core.processors.impl;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.io.Resource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.MessagingException;
import javax.mail.util.ByteArrayDataSource;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ba.unsa.etf.pnwt.microservice.config.ApplicationProperties;
import ba.unsa.etf.pnwt.microservice.core.method.CreateMimeMessageHelper;
import ba.unsa.etf.pnwt.microservice.core.method.IsValidEmail;
import ba.unsa.etf.pnwt.microservice.core.processors.EmailProcessor;
import ba.unsa.etf.pnwt.microservice.domain.model.Email;

@Component
@AllArgsConstructor
@Slf4j
public class SendEmailWithAttachment implements EmailProcessor {

    protected final static String NAME = "EMAIL_WITH_ATTACHMENT";
    private static final int MAX_RECIPIENTS = 98;
    private final JavaMailSender emailSender;
    private final IsValidEmail isValidEmail;
    private final ApplicationProperties config;
    private final CreateMimeMessageHelper createMimeMessageHelper;

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public void execute(final Email email) {
        try {
            final MimeMessageHelper mimeMessageHelper = createMimeMessageHelper.execute();
            mimeMessageHelper.setFrom(config.getUsername() != null ? config.getUsername() : email.getFrom());
            if (StringUtils.isNotBlank(email.getTo()) && isValidEmail.execute(email.getTo())) {
                mimeMessageHelper.setTo(email.getTo());
            }
            final LinkedList<String> trackSentBccEmailAddresses = new LinkedList<>();
            addBlinkCarbonCopy(email, trackSentBccEmailAddresses, mimeMessageHelper);
            mimeMessageHelper.setSubject(email.getSubject());
            mimeMessageHelper.setText(email.getMessage(), true);
            addAttachments(email, mimeMessageHelper);
            log.info("bcc trackSentBccEmailAddresses - {}", trackSentBccEmailAddresses.size());
            log.info("last email sent - {}", trackSentBccEmailAddresses.getLast());
            emailSender.send(mimeMessageHelper.getMimeMessage());
            if (trackSentBccEmailAddresses.size() >= MAX_RECIPIENTS && trackSentBccEmailAddresses.size() < email.getBcc().split(",").length) {
                final String emailToRemove = trackSentBccEmailAddresses.getLast();
                final int emailToRemoveIndex = email.getBcc().indexOf(emailToRemove);
                final int indexToRemoveFrom = emailToRemoveIndex + emailToRemove.length() + 1;
                final int index = indexToRemoveFrom < email.getBcc().length() ? indexToRemoveFrom : -1;
                if (index > 0 && StringUtils.isNotBlank(email.getBcc())) {
                    email.setBcc(email.getBcc().substring(index));
                    execute(email);
                }
            }
        } catch (MessagingException | IOException e) {
            log.error("An error occured while trying to send an email with message: {}", e.getMessage());
        }
    }

    private void addBlinkCarbonCopy(final Email email, final Collection<String> count, final MimeMessageHelper mimeMessageHelper) {
        final String bccAddressesCSV = StringUtils.replace(StringUtils.deleteWhitespace(StringUtils.lowerCase(email.getBcc())), "/", ",");
        if (StringUtils.isNotBlank(bccAddressesCSV)) {
            email.setBcc(bccAddressesCSV);
            final List<String> bccEmails = Arrays.asList(bccAddressesCSV.split(",").clone());
            CollectionUtils.emptyIfNull(bccEmails).stream()
                    .filter(StringUtils::isNoneBlank)
                    .map(StringUtils::normalizeSpace)
                    .map(bcc -> StringUtils.stripStart(bcc, ","))
                    .map(bcc -> StringUtils.stripEnd(bcc, ","))
                    .map(bcc -> bcc.replace(",", "."))
                    .filter(isValidEmail::execute)
                    .forEach(bcc -> {
                        try {
                            if (count.size() < MAX_RECIPIENTS) {
                                mimeMessageHelper.addBcc(bcc);
                                count.add(bcc);
                            }
                        } catch (MessagingException e) {
                            log.error("An error occured while trying to add email bcc with message: {}", e.getMessage());
                        }
                    });
        }
    }

    private void addAttachments(final Email email, final MimeMessageHelper mimeMessageHelper) throws IOException, MessagingException {
        for (MultipartFile multipartFile : email.getAttachments()) {
            if (multipartFile != null) {
                mimeMessageHelper.addAttachment(multipartFile.getName(), new ByteArrayDataSource(multipartFile.getInputStream(),
                        multipartFile.getContentType()));
            }
        }
    }

    @Override
    public boolean isApplicable(final Email email) {
        return email != null && CollectionUtils.isNotEmpty(email.getAttachments());
    }

    private byte[] getContent(final Resource file) {
        try {
            return file.getInputStream().readAllBytes();
        } catch (IOException e) {
            log.error(String.format("Could not read content from resource %s", file.getFilename()));
        }
        return null;
    }
}
