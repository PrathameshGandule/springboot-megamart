package com.infosys.megamart.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    @NotBlank(message = "firstName must not be blank")
    public String firstName;
    @NotBlank(message = "lastName must not be blank")
    public String lastName;
    @NotBlank(message = "email must not be blank")
    public String email;
    @NotBlank(message = "password must not be blank")
    @Pattern(
            regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$",
            message = "Password must be at least 8 characters long and contain one uppercase, one lowercase, one number, and one special character."
    )
    public String password;
    @NotBlank(message = "confirmPassword must not be blank")
    public String confirmPassword;
}
