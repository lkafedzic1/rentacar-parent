package ba.unsa.etf.pnwt.microservice.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import ba.unsa.etf.pnwt.microservice.controller.model.Car;
import ba.unsa.etf.pnwt.microservice.core.AdminService;

import static ba.unsa.etf.pnwt.microservice.adapter.Mappers.ADMIN;

@RestController
@AllArgsConstructor
public class AdminApiController implements AdminApi {

    private final AdminService adminService;

    @Override
    public ResponseEntity<Car> createCar(@Valid Car car) {
        return ResponseEntity.ok(ADMIN.domainToDto(adminService.createCar(ADMIN.dtoTodomain(car))));
    }

    @Override
    public ResponseEntity<Car> deleteCar(Long carId) {
        adminService.deleteCar(carId);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<List<Car>> getAllCars(@Valid Boolean isReserved) {
        return ResponseEntity.ok(adminService.getAllCars(isReserved).stream().map(ADMIN::domainToDto).collect(Collectors.toList()));
    }

    @Override
    public ResponseEntity<Car> getCarById(Long carId) {
        return ResponseEntity.ok(ADMIN.domainToDto(adminService.getCar(carId)));
    }

    @Override
    public ResponseEntity<List<Car>> search(@Valid String address, @Valid String from, @Valid String to) {
        return getAllCars(false);
    }

    @Override
    public ResponseEntity<Car> updateCar(Long carId, @Valid Car car) {
        return ResponseEntity.ok(ADMIN.domainToDto(adminService.updateCar(carId, ADMIN.dtoTodomain(car))));
    }
}
