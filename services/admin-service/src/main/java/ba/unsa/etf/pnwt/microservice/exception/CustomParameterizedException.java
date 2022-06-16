package ba.unsa.etf.pnwt.microservice.exception;

import org.zalando.problem.AbstractThrowableProblem;
import org.zalando.problem.Status;
import org.zalando.problem.StatusType;
import org.zalando.problem.ThrowableProblem;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class CustomParameterizedException extends AbstractThrowableProblem {

    CustomParameterizedException(String message, StatusType statusType, String details, ThrowableProblem throwableProblem, Map<String, Object> paramMap) {
        super(ErrorConstants.PARAMETERIZED_TYPE, "Parameterized Exception", (Objects.nonNull(statusType) ? statusType : Status.BAD_REQUEST), details, (URI)null,
                throwableProblem, toProblemParameters(message, paramMap));
    }

    public static CustomParameterizedException singleParameterProblem(String message, int statusCode, String key, Object value) {
        HashMap<String, Object> parameters = new HashMap();
        parameters.put(key, value);
        return new CustomParameterizedException(message, Status.valueOf(statusCode), message, null, parameters);
    }

    public static Map<String, Object> toProblemParameters(String message, Map<String, Object> paramMap) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("message", message);
        parameters.put("params", paramMap);
        return parameters;
    }

    public static CustomParameterizedException singleParameterProblem(String message, String key, Object value) {
        HashMap<String, Object> parameters = new HashMap();
        parameters.put(key, value);
        return new CustomParameterizedException(message, null, null, null, parameters);
    }
}
