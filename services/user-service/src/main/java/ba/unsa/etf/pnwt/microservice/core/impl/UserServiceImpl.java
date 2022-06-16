package ba.unsa.etf.pnwt.microservice.core.impl;


import ba.unsa.etf.pnwt.microservice.adapter.Mappers;
import ba.unsa.etf.pnwt.microservice.core.UserService;
import ba.unsa.etf.pnwt.microservice.core.method.DetermineCredentials;
import ba.unsa.etf.pnwt.microservice.domain.model.User;
import ba.unsa.etf.pnwt.microservice.domain.repository.UserRepository;
import ba.unsa.etf.pnwt.microservice.exception.CustomParameterizedException;
import ba.unsa.etf.pnwt.microservice.exception.ErrorMessages;
import ba.unsa.etf.pnwt.microservice.utility.Constant;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import ba.unsa.etf.pnwt.microservice.core.method.DeterminePasswordHash;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final DeterminePasswordHash determinePasswordHash;
    private final DetermineCredentials determineCredentials;

    @Override
    @Transactional
    public User registerUser(final String basicAuth, final User user) {

        final Map<String, String> credentials = determineCredentials.execute(basicAuth);
        if (isUser(user.getEmail())) {
            throw CustomParameterizedException.singleParameterProblem(ErrorMessages.DUPLICATE_USERNAME, Constant.USERNAME, user.getEmail());
        }

        user.setPassword(determinePasswordHash.execute(credentials.get(Constant.PASSWORD)));
        return Mappers.USER.entityToDomain(userRepository.save(Mappers.USER.domainToEntity(user)));
    }

    @Override
    public User login(final String basicAuth) {
        final BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        final Map<String, String> credentials = determineCredentials.execute(basicAuth);
        final String username = credentials.get(Constant.USERNAME);
        final String password = credentials.get(Constant.PASSWORD);
        if (!isUser(username) || StringUtils.isEmpty(password)) {
            throw CustomParameterizedException.singleParameterProblem(ErrorMessages.INCORRECT_USERNAME_PASSWORD, Constant.USERNAME, username);
        }
        final User user = getUserByEmail(username);
        if (!bCryptPasswordEncoder.matches(password, user.getPassword())) {
            throw CustomParameterizedException.singleParameterProblem(ErrorMessages.INCORRECT_USERNAME_PASSWORD, Constant.USERNAME, username);
        }
        return user;
    }

    @Override
    public boolean isUser(final String email) {
        return userRepository.findAll().stream().anyMatch(user -> user.getEmail().equalsIgnoreCase(email));
    }

    @Override
    public User getUserByEmail(final String email) {
        return Mappers.USER.entityToDomain(userRepository.findAll().stream().filter(user -> user.getEmail().equals(email)).findFirst().orElse(null));
    }

    @Override
    public User getUser(final long userId) {
        return Mappers.USER.entityToDomain(userRepository.findById(userId).orElseThrow(() -> CustomParameterizedException.singleParameterProblem(ErrorMessages.USER_NOT_FOUND,
                HttpStatus.NOT_FOUND.value(),
                Constant.USER_ID, userId)));
    }

    @Override
    public User updateUser(final long userId, final User user) {
        user.setId(userId);
        final User savedUser = getUser(userId);
        Mappers.USER.update(savedUser, user);
        return Mappers.USER.entityToDomain(userRepository.save(Mappers.USER.domainToEntity(savedUser)));
    }

    @Override
    public void deleteUser(final long userId) {
        userRepository.deleteById(userId);
    }

    @Override
    public Collection<User> getAllUsers() {
        return CollectionUtils.emptyIfNull(userRepository.findAll())
                .stream().map(Mappers.USER::entityToDomain)
                .collect(Collectors.toList());
    }

}
