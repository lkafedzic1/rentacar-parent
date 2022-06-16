package ba.unsa.etf.pnwt.microservice.core.processors.impl;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Component;

import java.util.Collection;
import lombok.AllArgsConstructor;
import ba.unsa.etf.pnwt.microservice.domain.model.Email;

@Component
@AllArgsConstructor
public class EmailProcessor {
    final Collection<ba.unsa.etf.pnwt.microservice.core.processors.EmailProcessor> emailProcessors;

    public void execute(final Email email) {
        CollectionUtils.emptyIfNull(emailProcessors).stream()
                .filter(emailProcessor -> emailProcessor.isApplicable(email))
                .findFirst()
                .ifPresent(emailProcessor -> emailProcessor.execute(email));
    }
}
