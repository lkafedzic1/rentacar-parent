package ba.unsa.etf.pnwt.microservice.core.impl;

import ba.unsa.etf.pnwt.microservice.core.processors.impl.EmailProcessor;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import ba.unsa.etf.pnwt.microservice.core.EmailService;
import ba.unsa.etf.pnwt.microservice.domain.model.Email;

@Component
@AllArgsConstructor
public class EmailServiceImpl implements EmailService {

    private final EmailProcessor emailProcessor;

    @Override
    public void sendEmail(final Email email) {
        emailProcessor.execute(email);
    }
}
