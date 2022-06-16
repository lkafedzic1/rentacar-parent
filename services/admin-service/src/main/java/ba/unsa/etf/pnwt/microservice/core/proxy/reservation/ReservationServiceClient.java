package ba.unsa.etf.pnwt.microservice.core.proxy.reservation;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import ba.unsa.etf.pnwt.microservice.core.proxy.reservation.model.Reservation;

@FeignClient("reservation-service")
public interface ReservationServiceClient {

    @GetMapping("/reservations/{reservationId}")
    Reservation getReservationById(@PathVariable("reservationId") final Long reservationId);

    @DeleteMapping("/reservations/cars/{carId}")
    Reservation deleteAllReservationByCarId(@PathVariable("carId") final Long carId);
}
