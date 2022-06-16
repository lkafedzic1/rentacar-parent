package ba.unsa.etf.pnwt.microservice.utility;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import ba.unsa.etf.pnwt.microservice.adapter.Mappers;
import ba.unsa.etf.pnwt.microservice.core.method.DeterminePasswordHash;
import ba.unsa.etf.pnwt.microservice.domain.model.Role;
import ba.unsa.etf.pnwt.microservice.domain.model.User;
import ba.unsa.etf.pnwt.microservice.domain.repository.RoleRepository;
import ba.unsa.etf.pnwt.microservice.domain.repository.UserRepository;

@Component
@AllArgsConstructor
public class DataGenerater implements CommandLineRunner {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final DeterminePasswordHash passwordHash;

    @Override
    public void run(String... args) throws Exception {
        roleRepository.save(ba.unsa.etf.pnwt.microservice.persistance.Role.builder().id(1L).name("ADMIN").build());
        List<User> users = new ArrayList<>();
        users.add(User.builder().id(1L).firstName("Jabulo").lastName("Ndalo").role(Role.builder().name("ADMIN").build()).lastName("Zulu").email(
                "testmaildev10@gmail.com").phone(
                "6406170247087").password(passwordHash.execute(
                "password1")).build());
        users.add(User.builder().id(2L).firstName("Sakhile").lastName("Khona").role(Role.builder().name("USER").build()).lastName("Langa").email(
                "testmaildev11@gmail.com").phone(
                "7704226625085").password(passwordHash.execute(
                "password1")).build());
        users.add(User.builder().id(3L).firstName("Sithembiso").lastName("Khona").role(Role.builder().name("USER").build()).lastName("Ndlovu").email(
                "testmaildev12@gmail.com").phone(
                "9901030292080").password(passwordHash.execute(
                "password1")).build());
        users.add(User.builder().id(4L).firstName("Zikhona").lastName("Khona").role(Role.builder().name("USER").build()).lastName("Ndlovu").email(
                "testmaildev13@gmail.com").phone(
                "9901030292080").password(passwordHash.execute(
                "password1")).build());
        users.stream().map(Mappers.USER::domainToEntity).forEach(userRepository::save);
    }
}
