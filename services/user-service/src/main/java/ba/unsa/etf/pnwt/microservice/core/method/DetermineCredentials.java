package ba.unsa.etf.pnwt.microservice.core.method;


import org.springframework.stereotype.Component;

import java.util.Map;
import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class DetermineCredentials {
    private final AuthorizationHearderExtractor extractor;

    public Map<String, String> execute(String basicAuth) {
        return extractor.extractAuthenticationDetails(basicAuth);
    }
}
