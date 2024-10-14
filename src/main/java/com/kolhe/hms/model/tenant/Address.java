package com.kolhe.hms.model.tenant;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotBlank(message = "Address cannot be blank")
    @Size(min = 1, max = 300, message = "Address line 1 cannot be more than 300 characters")
    private String addressLine1;
    @Size(min = 1, max = 300, message = "Address line 2 cannot be more than 300 characters")
    private String addressLine2;
    @Size(min = 1, max = 50, message = "City cannot be more than 50 characters")
    @NotBlank(message = "City cannot be blank")
    private String city;
    @Size(min = 1, max = 20, message = "State cannot be more than 20 characters")
    @NotBlank(message = "State cannot be blank")
    private String state;
    @Size(min = 1, max = 10, message = "Pincode cannot be more than 10 characters")
    @NotBlank(message = "Pincode cannot be blank")
    private String pincode;
    @Size(min = 1, max = 20, message = "Country cannot be more than 20 characters")
    @NotBlank(message = "Country cannot be blank")
    private String country;
}
