package ba.unsa.etf.pnwt.microservice.exception;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FieldErrorVM implements Serializable {
    private static final long serialVersionUID = 7613168076010957127L;

    private final String objectName;
    private final String field;
    private final String message;
}
