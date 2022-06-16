package ba.unsa.etf.pnwt.microservice.domain.repository;

import ba.unsa.etf.pnwt.microservice.persistance.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
}
