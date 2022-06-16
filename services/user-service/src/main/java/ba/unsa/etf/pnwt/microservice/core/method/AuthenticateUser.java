package ba.unsa.etf.pnwt.microservice.core.method;

import ba.unsa.etf.pnwt.microservice.domain.model.ActiveUser;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import ba.unsa.etf.pnwt.microservice.core.UserService;

@Service
@AllArgsConstructor
public class AuthenticateUser implements UserDetailsService {
    private final UserService userService;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        if (!userService.isUser(s)) {
            throw new UsernameNotFoundException("username or password incorrect");
        }
        return new ActiveUser(userService.getUserByEmail(s));
    }
}
