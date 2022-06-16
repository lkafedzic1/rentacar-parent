package ba.unsa.etf.pnwt.microservice.core.method;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class DeterminePasswordHash {

    public String execute(String password) {
        //NB WE USE A NEW ONE TO AVOID CIRCULAR DEPENCY
        return new BCryptPasswordEncoder().encode(password);
    }
}
