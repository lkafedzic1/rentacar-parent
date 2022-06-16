package ba.unsa.etf.pnwt.microservice.utility;

import ba.unsa.etf.pnwt.microservice.adapter.Mappers;
import ba.unsa.etf.pnwt.microservice.domain.repository.AdminRepository;
import com.google.gson.Gson;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import lombok.AllArgsConstructor;
import ba.unsa.etf.pnwt.microservice.domain.model.Car;

@Component
@AllArgsConstructor
public class DataGenerater implements CommandLineRunner {

    private final static String CARS = "[\n   {\n      \"id\":1,\n      \"name\":\"Toyota Corolla\",\n      \"passengerCount\":4,\n      " +
            "\"fuelType\":\"Petrol\",\n      \"price\":150.0,\n      \"transmissionType\":\"Automatic\",\n      \"imageUrl\":\"https://i.imgur.com/ftN1n8C" +
            ".jpeg\"\n   },\n   {\n      \"id\":2,\n      \"name\":\"Toyota Urban Cruiser\",\n      \"passengerCount\":5,\n      \"fuelType\":\"Desiel\",\n  " +
            "    \"price\":100.0,\n      \"transmissionType\":\"Manual\",\n      \"imageUrl\":\"https://i.imgur.com/Ln697Gw.jpg\"\n   },\n   {\n      " +
            "\"id\":3,\n      \"name\":\"Toyota Land Cruiser\",\n      \"passengerCount\":6,\n      \"fuelType\":\"Petrol\",\n      \"price\":170.0,\n      " +
            "\"transmissionType\":\"Automatic\",\n      \"imageUrl\":\"https://i.imgur.com/mdYTEqi.jpg\"\n   },\n   {\n      \"id\":4,\n      " +
            "\"name\":\"Toyota Quantum\",\n      \"passengerCount\":4,\n      \"fuelType\":\"Petrol\",\n      \"price\":250.0,\n      " +
            "\"transmissionType\":\"Automatic\",\n      \"imageUrl\":\"https://i.imgur.com/Hrk1v4u.jpg\"\n   },\n   {\n      \"id\":5,\n      " +
            "\"name\":\"Lexus NX\",\n      \"passengerCount\":5,\n      \"fuelType\":\"Petrol\",\n      \"price\":300.0,\n      " +
            "\"transmissionType\":\"Automatic\",\n      \"imageUrl\":\"https://i.imgur.com/huBXAIR.jpg\"\n   },\n   {\n      \"id\":6,\n      " +
            "\"name\":\"Lexus LN\",\n      \"passengerCount\":4,\n      \"fuelType\":\"Petrol\",\n      \"price\":180.0,\n      " +
            "\"transmissionType\":\"Automatic\",\n      \"imageUrl\":\"https://i.imgur.com/qFGCxg6.jpg\"\n   }\n]";
    private AdminRepository adminRepository;


    @Override
    public void run(String... args) throws Exception {

        final Gson gson = new Gson();
        final List<Car> cars = Arrays.asList(gson.fromJson(CARS, Car[].class));
        //        cars.add(Car.builder().name("product 1").description("description").price(BigDecimal.ONE).build());
        //        cars.add(Car.builder().name("product 2").reserved(true).description("description").price(BigDecimal.TEN).build());
        //        cars.add(Car.builder().name("product 3").description("description").price(BigDecimal.ONE).build());
        cars.stream().map(Mappers.ADMIN::domainToEntity).forEach(product -> adminRepository.save(product));
    }
}
