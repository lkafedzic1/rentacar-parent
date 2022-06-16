package ba.unsa.etf.pnwt.microservice.core.processors;

import ba.unsa.etf.pnwt.microservice.domain.model.Email;

public interface EmailProcessor {
    String getName();

    void execute(Email email);

    boolean isApplicable(Email email);
}
