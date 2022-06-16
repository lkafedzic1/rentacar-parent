package ba.unsa.etf.pnwt.microservice.domain.model;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Invoice {

    private Long id;
    private long reservationId;
    private long userId;
    private LocalDate createdAt;
}
