package ba.unsa.etf.pnwt.microservice.core.impl;


import ba.unsa.etf.pnwt.microservice.adapter.Mappers;
import ba.unsa.etf.pnwt.microservice.core.AdminService;
import ba.unsa.etf.pnwt.microservice.core.proxy.reservation.ReservationServiceClient;
import ba.unsa.etf.pnwt.microservice.domain.model.Car;
import ba.unsa.etf.pnwt.microservice.domain.repository.AdminRepository;
import ba.unsa.etf.pnwt.microservice.exception.CustomParameterizedException;
import ba.unsa.etf.pnwt.microservice.exception.ErrorMessages;
import ba.unsa.etf.pnwt.microservice.utility.Constant;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Objects;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AdminServiceImpl implements AdminService {

    private final AdminRepository adminRepository;
    private final ReservationServiceClient reservationServiceClient;

    @Override
    public Car createCar(final Car car) {
        return Mappers.ADMIN.entityToDomain(adminRepository.save(Mappers.ADMIN.domainToEntity(car)));
    }

    @Override
    public Car getCar(final long id) {
        return Mappers.ADMIN.entityToDomain(adminRepository.findById(id).orElseThrow(() -> CustomParameterizedException.singleParameterProblem(ErrorMessages.CAR_NOT_FOUND,
                HttpStatus.NOT_FOUND.value(),
                Constant.CAR_ID, id)));
    }

    @Override
    public Collection<Car> getAllCars(final Boolean isReserverd) {
        if (isReserverd != null) {
            return adminRepository.findAll().stream().filter(car -> Objects.equals(isReserverd, car.isReserved())).map(Mappers.ADMIN::entityToDomain).collect(Collectors.toList());
        }
        return adminRepository.findAll().stream().map(Mappers.ADMIN::entityToDomain).collect(Collectors.toList());
    }

    @Override
    public Car updateCar(final long id, final Car car) {
        car.setId(id);
        final Car carToUpdate = getCar(id);
        Mappers.ADMIN.update(carToUpdate, car);
        return Mappers.ADMIN.entityToDomain(adminRepository.save(Mappers.ADMIN.domainToEntity(carToUpdate)));
    }

    @Override
    public void deleteCar(final long id) {
        reservationServiceClient.deleteAllReservationByCarId(id);
        adminRepository.deleteById(id);
    }
}
