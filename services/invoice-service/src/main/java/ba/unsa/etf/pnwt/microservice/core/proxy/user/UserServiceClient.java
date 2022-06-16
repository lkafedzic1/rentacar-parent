package ba.unsa.etf.pnwt.microservice.core.proxy.user;

import ba.unsa.etf.pnwt.microservice.core.proxy.user.model.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("user-service")
public interface UserServiceClient {

    @GetMapping("/users/{userId}")
    User getUserById(@PathVariable("userId") final Long userId);
}
