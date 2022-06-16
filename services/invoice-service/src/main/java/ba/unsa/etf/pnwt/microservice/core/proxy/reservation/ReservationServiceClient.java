package ba.unsa.etf.pnwt.microservice.core.proxy.reservation;

import ba.unsa.etf.pnwt.microservice.core.proxy.reservation.model.Reservation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("reservation-service")
public interface ReservationServiceClient {

    @GetMapping("/reservations/{reservationId}")
    Reservation getReservationById(@PathVariable("reservationId") final Long reservationId);
}
