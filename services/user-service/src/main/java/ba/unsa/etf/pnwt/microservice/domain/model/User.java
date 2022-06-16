package ba.unsa.etf.pnwt.microservice.domain.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import javax.validation.constraints.NotBlank;
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
    @NotBlank(message = "email is mandatory")
    private String email;
    private String phone;
    @NotBlank(message = "password is mandatory")
    private String password;
    private Address address;
    private Role role;

}
