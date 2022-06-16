package ba.unsa.etf.pnwt.microservice.core;

import java.util.Collection;

import ba.unsa.etf.pnwt.microservice.domain.model.User;

public interface UserService {

    User registerUser(String basicAuth, User user);

    User login(String basicAuth);

    boolean isUser(String username);

    User getUserByEmail(String email);

    User getUser(long userId);

    User updateUser(long userId, User user);

    void deleteUser(long userId);

    Collection<User> getAllUsers();
}
