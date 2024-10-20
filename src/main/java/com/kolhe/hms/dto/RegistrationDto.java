package com.kolhe.hms.dto;

import com.kolhe.hms.model.tenant.EnumRole;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegistrationDto {
    @Size(min = 1, max = 20, message = "First name must be between 1 and 20 characters")
    private String firstName;
    @Size(min = 0, max = 20, message = "Middle name must be less than 20 characters")
    private String middleName;
    @Size(min = 1, max = 20, message = "Last name must be between 1 and 20 characters")
    private String lastName;
    @Size(min = 1, max = 50, message = "Email cannot be more than 50 characters")
    @Email(message = "Email should be valid")
    @NotBlank(message = "Email is required")
    private String email;
    @Size(min = 10, max = 10, message = "Mobile must be 10 characters")
    @NotBlank(message = "Mobile number is required")
    private String mobile;
    private Integer mobileCountryCode;
    private LocalDateTime createdDate;
    private EnumRole role = EnumRole.TENANT;
    @Size(min = 5, max = 20, message = "User name must be unique and between 5 and 20 characters")
    private String userName;
    private String password;
    @NotBlank(message = "Whatsapp mobile is required")
    @Size(min = 10, max = 10, message = "Whatsapp mobile must be 10 characters")
    private String whatsapp;
}
