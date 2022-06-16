package ba.unsa.etf.pnwt.microservice.core.proxy.user.model;

import ba.unsa.etf.pnwt.microservice.domain.model.Address;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class User {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private Address address;
    private Role role;

}
