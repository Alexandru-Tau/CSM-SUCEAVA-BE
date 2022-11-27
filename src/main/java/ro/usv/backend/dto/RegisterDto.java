package ro.usv.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import ro.usv.backend.model.LocalUserRole;

@Getter
@Setter
@AllArgsConstructor
public class RegisterDto {
    private String email;

    private String username;

    private String password;

    private String confirmPassword;


    private LocalUserRole localUserRole;
}
