package ba.unsa.etf.pnwt.microservice.core.method;

import ba.unsa.etf.pnwt.microservice.utility.Constant;

import org.apache.commons.codec.binary.Base64;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class AuthorizationHearderExtractor {

    public Map<String, String> extractAuthenticationDetails(String basicAuthString) {
        String basicAuth = basicAuthString.substring("Bearer ".length()).trim();
        String decodedString = new String(Base64.decodeBase64(basicAuth.getBytes()));
        String[] credentialsArray = decodedString.split(":");
        Map<String, String> credentials = new HashMap<>();
        if(credentialsArray.length > 1) {
            credentials.put(Constant.USERNAME, credentialsArray[0]);
            credentials.put(Constant.PASSWORD, credentialsArray[1]);
        }

        return credentials;
    }
}
