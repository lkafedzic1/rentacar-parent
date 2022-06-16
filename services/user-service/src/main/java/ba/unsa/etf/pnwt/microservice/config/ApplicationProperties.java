package ba.unsa.etf.pnwt.microservice.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
@ConfigurationProperties("app")
public class ApplicationProperties {
    private AuthenticationConfig authenticationConfig;
    @Data
    public static class AuthenticationConfig {
        private Integer salt;
    }
}
