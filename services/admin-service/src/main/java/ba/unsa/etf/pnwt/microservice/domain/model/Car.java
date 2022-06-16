package ba.unsa.etf.pnwt.microservice.domain.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Car {

    private long id;
    private String name;
    private String description;
    private BigDecimal price;
    private boolean reserved;
    private long passengerCount;
    private String fuelType;
    private String transmissionType;
    private String imageUrl;
    private Address address;
}
