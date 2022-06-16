package ba.unsa.etf.pnwt.microservice.domain.repository;


import ba.unsa.etf.pnwt.microservice.persistance.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    Optional<Reservation> findById(Long aLong);
}
