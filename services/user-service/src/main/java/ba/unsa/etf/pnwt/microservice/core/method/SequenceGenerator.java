package ba.unsa.etf.pnwt.microservice.core.method;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;

@Component
public class SequenceGenerator {
    private long lastAdded = 5L;

    public long generateSequence() {
        lastAdded += 1 ;
        return lastAdded;
    }
}
