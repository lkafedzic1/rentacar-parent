package ba.unsa.etf.pnwt.microservice.core.method;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class CreateMimeMessageHelper {
    private final JavaMailSender emailSender;
    public MimeMessageHelper execute() throws MessagingException {
        return new MimeMessageHelper(emailSender.createMimeMessage(), true);
    }
}
