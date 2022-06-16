package ba.unsa.etf.pnwt.microservice.domain.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.math.BigDecimal;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Reservation {

    private Long id;
    private Long userId;
    private Long carId;
    private BigDecimal price;
    private Address address;
    private LocalDate toDate;
    private LocalDate fromDate;
}
