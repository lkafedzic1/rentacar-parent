package ba.unsa.etf.pnwt.microservice.core;

import java.util.Collection;

import ba.unsa.etf.pnwt.microservice.domain.model.Reservation;

public interface ReservationService {

    Reservation createReservation(Reservation order);

    Reservation getReservation(long id);

    Collection<Reservation> getAllReservations();

    Reservation updateReservation(long id, Reservation order);

    void deleteReservation(long id);

    void deleteAllReservationByCarId(long id);
}
