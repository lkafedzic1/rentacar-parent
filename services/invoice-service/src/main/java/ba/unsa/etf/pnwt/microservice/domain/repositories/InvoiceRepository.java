package ba.unsa.etf.pnwt.microservice.domain.repositories;

import ba.unsa.etf.pnwt.microservice.domain.entities.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;


public interface InvoiceRepository extends JpaRepository<Invoice, Long> {
    Collection<Invoice> getAllByUserId(long userId);

    Collection<Invoice> getAllByReservationId(long reservationId);

}
