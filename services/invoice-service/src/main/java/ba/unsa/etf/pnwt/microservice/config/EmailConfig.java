package ba.unsa.etf.pnwt.microservice.config;

import org.springframework.context.annotation.Configuration;

import lombok.AllArgsConstructor;

@Configuration
@AllArgsConstructor
public class EmailConfig {
    private final ApplicationProperties applicationProperties;
}
