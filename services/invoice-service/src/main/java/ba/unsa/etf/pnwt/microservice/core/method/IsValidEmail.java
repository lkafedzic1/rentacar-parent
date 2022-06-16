package ba.unsa.etf.pnwt.microservice.core.method;

import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

@Component
public class IsValidEmail {
    final Pattern pattern = Pattern.compile("^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$");
    public boolean execute(final String email) {
        return pattern.matcher(email).matches();
    }
}
