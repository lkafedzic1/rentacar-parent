package ba.unsa.etf.pnwt.microservice.utility;

import ba.unsa.etf.pnwt.microservice.persistance.Reservation;
import com.google.gson.Gson;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import lombok.AllArgsConstructor;
import ba.unsa.etf.pnwt.microservice.domain.repository.ReservationRepository;

@Component
@AllArgsConstructor
public class DataGenerater implements CommandLineRunner {

    private final static String RESERVATIONS = "[{\"id\":1,\"userId\":1,\"carId\":4,\"price\":150.0,\"address\":{\"street\":\"Freedomstreet\",\"country\":\"USA\",\"city\":\"NewYork\"},\"toDate\":\"2022-05-28\",\"fromDate\":\"2022-05-28\"},{\"id\":2,\"userId\":3,\"carId\":3,\"price\":150.0,\"address\":{\"street\":\"Freedomstreet\",\"country\":\"USA\",\"city\":\"NewYork\"},\"toDate\":\"2022-06-28\",\"fromDate\":\"2022-06-28\"},{\"id\":3,\"userId\":4,\"carId\":2,\"price\":150.0,\"address\":{\"street\":\"Freedomstreet\",\"country\":\"USA\",\"city\":\"NewYork\"},\"toDate\":\"2022-07-28\",\"fromDate\":\"2022-07-28\"},{\"id\":4,\"userId\":3,\"carId\":1,\"price\":150.0,\"address\":{\"street\":\"Freedomstreet\",\"country\":\"USA\",\"city\":\"NewYork\"},\"toDate\":\"2022-08-28\",\"fromDate\":\"2022-08-28\"}]";
    private final ReservationRepository orderRepository;
    private final Gson gson;

    @Override
    public void run(String... args) throws Exception {
        List<Reservation> orders = Arrays.asList(gson.fromJson(RESERVATIONS, Reservation[].class));
        orderRepository.saveAll(orders);
    }
}
