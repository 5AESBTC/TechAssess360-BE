package com.example.sourcebase.domain.dto.reqdto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = lombok.AccessLevel.PRIVATE)
public class RegisterReqDTO {

    @NotBlank(message = "Name must not be blank")
    @Size(min = 2, max = 50, message = "Name must be between 2 and 50 characters")
    String name;

    @Email(message = "Invalid email format")
    @NotBlank(message = "Email must not be blank")
    String email;

    @NotBlank(message = "Phone number must not be blank")
    @Pattern(regexp = "^(\\+?\\d{1,3})?\\d{10}$", message = "Invalid phone number format")
    String phoneNumber;

    @NotBlank(message = "Date of birth must not be blank")
    @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$", message = "Date of birth must be in the format YYYY-MM-DD")
    String dob;

    @NotBlank(message = "Gender must not be blank")
    @Pattern(regexp = "^(Male|Female|Other)$", message = "Gender must be Male, Female, or Other")
    String gender;

    @NotBlank(message = "Type of user must not be blank")
    String typeUser;

    @NotBlank(message = "Username must not be blank")
    @Size(min = 3, max = 20, message = "Username must be between 3 and 20 characters")
    String username;

    @NotBlank(message = "Password must not be blank")
    @Size(min = 6, message = "Password must be at least 6 characters")
    String password;
}
