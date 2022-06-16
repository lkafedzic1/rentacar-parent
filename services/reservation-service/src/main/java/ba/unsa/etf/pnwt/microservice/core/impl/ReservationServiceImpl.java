package ba.unsa.etf.pnwt.microservice.core.impl;


import ba.unsa.etf.pnwt.microservice.adapter.Mappers;
import ba.unsa.etf.pnwt.microservice.core.ReservationService;
import ba.unsa.etf.pnwt.microservice.core.proxy.admin.AdminServiceClient;
import ba.unsa.etf.pnwt.microservice.core.proxy.admin.model.Car;
import ba.unsa.etf.pnwt.microservice.core.proxy.user.UserServiceClient;
import ba.unsa.etf.pnwt.microservice.domain.model.Reservation;
import ba.unsa.etf.pnwt.microservice.domain.repository.ReservationRepository;
import ba.unsa.etf.pnwt.microservice.exception.CustomParameterizedException;
import ba.unsa.etf.pnwt.microservice.exception.ErrorMessages;
import ba.unsa.etf.pnwt.microservice.utility.Constant;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@AllArgsConstructor
@Slf4j
public class ReservationServiceImpl implements ReservationService {

    private final ReservationRepository reservationRepository;
    private final AdminServiceClient adminServiceClient;
    private final UserServiceClient userServiceClient;

    @Override
    @Transactional
    public Reservation createReservation(final Reservation reservation) {
        validate(reservation);
        adminServiceClient.updateCar(reservation.getCarId(), Car.builder().reserved(true).build());
        return Mappers.RESERVATION.entityToDomain(reservationRepository.save(Mappers.RESERVATION.domainToEntity(reservation)));
    }

    @Override
    public Reservation getReservation(final long id) {
        return Mappers.RESERVATION.entityToDomain(reservationRepository.findById(id).orElseThrow(() ->
                CustomParameterizedException.singleParameterProblem(ErrorMessages.RESERVATION_NOT_FOUND,
                        HttpStatus.NOT_FOUND.value(),
                        Constant.RESERVATION_ID, id)));
    }

    @Override
    public Collection<Reservation> getAllReservations() {
        return CollectionUtils.emptyIfNull(reservationRepository.findAll())
                .stream()
                .map(Mappers.RESERVATION::entityToDomain)
                .collect(Collectors.toList());
    }

    @Override
    public Reservation updateReservation(final long id, final Reservation reservation) {
        validate(reservation);
        getReservation(id);
        reservation.setId(id);
        adminServiceClient.updateCar(reservation.getCarId(), Car.builder().reserved(true).build());
        return Mappers.RESERVATION.entityToDomain(reservationRepository.save(Mappers.RESERVATION.domainToEntity(reservation)));
    }

    @Override
    public void deleteReservation(final long id) {
        try {
            final Reservation reservation = getReservation(id);
            validate(reservation);
            reservationRepository.deleteById(id);
            adminServiceClient.updateCar(reservation.getCarId(), Car.builder().reserved(false).build());
        } catch (Exception e) {
            log.error("Error while deleting reservation with id {}", id);
        }

    }

    @Override
    public void deleteAllReservationByCarId(long id) {
        reservationRepository.findAll().stream()
                .filter(reservation -> reservation.getCarId().equals(id))
                .forEach(reservation -> {
                    reservationRepository.deleteById(reservation.getId());
                });
    }


    private void validate(final Reservation reservation) {
        //         validate user
        userServiceClient.getUserById(reservation.getUserId());
        //         validate car
        adminServiceClient.findCarById(reservation.getCarId());
    }
}
