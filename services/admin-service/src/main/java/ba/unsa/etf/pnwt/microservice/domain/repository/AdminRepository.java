package ba.unsa.etf.pnwt.microservice.domain.repository;

import ba.unsa.etf.pnwt.microservice.persistance.Car;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

public interface AdminRepository extends JpaRepository<Car, Long> {
    Collection<Car> getAllByReserved(boolean reserved);
}
