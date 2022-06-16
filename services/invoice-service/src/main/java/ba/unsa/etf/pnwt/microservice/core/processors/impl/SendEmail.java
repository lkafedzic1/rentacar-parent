package ba.unsa.etf.pnwt.microservice.core.processors.impl;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import ba.unsa.etf.pnwt.microservice.core.processors.EmailProcessor;
import ba.unsa.etf.pnwt.microservice.domain.model.Email;

@Component
@AllArgsConstructor
public class SendEmail implements EmailProcessor {

    protected final static String NAME = "EMAIL";
    private final JavaMailSender emailSender;

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public void execute(Email email) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(email.getFrom());
        message.setTo(email.getTo());
        message.setSubject(email.getSubject());
        message.setText(email.getMessage());
        emailSender.send(message);
    }

    @Override
    public boolean isApplicable(Email email) {
        return email != null && CollectionUtils.isEmpty(email.getAttachments());
    }
}
