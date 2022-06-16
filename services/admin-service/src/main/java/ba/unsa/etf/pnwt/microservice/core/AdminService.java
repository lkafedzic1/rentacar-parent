package ba.unsa.etf.pnwt.microservice.core;

import java.util.Collection;

import ba.unsa.etf.pnwt.microservice.domain.model.Car;

public interface AdminService {

    Car createCar(Car car);

    Car getCar(long id);

    Collection<Car> getAllCars(Boolean isReserved);

    Car updateCar(long id, Car car);

    void deleteCar(long id);
}
