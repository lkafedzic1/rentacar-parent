package ba.unsa.etf.pnwt.microservice.core;

import ba.unsa.etf.pnwt.microservice.domain.model.Email;

public interface EmailService {
    void sendEmail(Email email);
}
