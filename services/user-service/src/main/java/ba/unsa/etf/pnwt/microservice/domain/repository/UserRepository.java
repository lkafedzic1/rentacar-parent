package ba.unsa.etf.pnwt.microservice.domain.repository;

import ba.unsa.etf.pnwt.microservice.persistance.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
