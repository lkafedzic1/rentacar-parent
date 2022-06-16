package ba.unsa.etf.pnwt.microservice.controller;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import ba.unsa.etf.pnwt.microservice.adapter.Mappers;
import ba.unsa.etf.pnwt.microservice.controller.model.User;
import ba.unsa.etf.pnwt.microservice.core.UserService;

@RestController
@AllArgsConstructor
public class UserApiController implements UsersApi {

    private final UserService userService;

    @Override
    public ResponseEntity<User> createUser(@Valid User user, String authentication) {
        return ResponseEntity.ok(Mappers.USER.domainToDto(userService.registerUser(authentication, Mappers.USER.dtoTodomain(user))));
    }

    @Override
    public ResponseEntity<User> deleteUser(Long userId) {
        userService.deleteUser(userId);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(CollectionUtils.emptyIfNull(userService.getAllUsers())
                .stream()
                .map(Mappers.USER::domainToDto)
                .collect(Collectors.toList()));
    }

    @Override
    public ResponseEntity<User> getUserById(Long userId) {
        return ResponseEntity.ok(Mappers.USER.domainToDto(userService.getUser(userId)));
    }

    @Override
    public ResponseEntity<Object> isUser(@NotNull @Valid String username) {
        return ResponseEntity.ok(userService.isUser(username));
    }

    @Override
    public ResponseEntity<User> login(String authorization) {
        return ResponseEntity.ok(Mappers.USER.domainToDto(userService.login(authorization)));
    }

    @Override
    public ResponseEntity<User> updateUser(Long userId, @Valid User user) {
        return ResponseEntity.ok(Mappers.USER.domainToDto(userService.updateUser(userId, Mappers.USER.dtoTodomain(user))));
    }
}
