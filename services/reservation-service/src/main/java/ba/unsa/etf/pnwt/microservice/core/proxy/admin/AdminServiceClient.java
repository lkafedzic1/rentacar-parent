package ba.unsa.etf.pnwt.microservice.core.proxy.admin;

import ba.unsa.etf.pnwt.microservice.core.proxy.admin.model.Car;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@FeignClient("admin-service")
public interface AdminServiceClient {

    @GetMapping("/admin/cars/{carId}")
    Car findCarById(@PathVariable("carId") final Long carId);

    @PutMapping("/admin/cars/{carId}")
    Car updateCar(@PathVariable("carId") final Long carId, @RequestBody final Car car);
}
