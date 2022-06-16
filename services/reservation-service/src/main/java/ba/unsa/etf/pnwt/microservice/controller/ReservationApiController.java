package ba.unsa.etf.pnwt.microservice.controller;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import ba.unsa.etf.pnwt.microservice.adapter.Mappers;
import ba.unsa.etf.pnwt.microservice.controller.model.Reservation;
import ba.unsa.etf.pnwt.microservice.core.ReservationService;

@RestController
@AllArgsConstructor
public class ReservationApiController implements ReservationsApi {

    private final ReservationService reservationService;

    @Override
    public ResponseEntity<Reservation> createReservation(@Valid Reservation reservation) {
        return ResponseEntity.ok(Mappers.RESERVATION.domainToDto(reservationService.createReservation(Mappers.RESERVATION.dtoTodomain(reservation))));
    }

    @Override
    public ResponseEntity<Void> deleteAllReservationByCarId(Long carId) {
        reservationService.deleteAllReservationByCarId(carId);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<Void> deleteReservation(Long reservationId) {
        reservationService.deleteReservation(reservationId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Override
    public ResponseEntity<List<Reservation>> getAllReservations() {
        return ResponseEntity.ok(CollectionUtils.emptyIfNull(reservationService.getAllReservations())
                .stream()
                .map(Mappers.RESERVATION::domainToDto)
                .collect(Collectors.toList()));
    }

    @Override
    public ResponseEntity<Reservation> getReservationById(Long reservationId) {
        return ResponseEntity.ok(Mappers.RESERVATION.domainToDto(reservationService.getReservation(reservationId)));
    }

    @Override
    public ResponseEntity<Reservation> updateReservation(Long reservationId, @Valid Reservation reservation) {
        return ResponseEntity.ok(Mappers.RESERVATION.domainToDto(reservationService.updateReservation(reservationId, Mappers.RESERVATION.dtoTodomain(reservation))));
    }

}
