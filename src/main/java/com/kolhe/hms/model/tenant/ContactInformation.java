package com.kolhe.hms.model.tenant;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContactInformation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @JoinColumn(name = "address_id")
    @ManyToOne(cascade = CascadeType.ALL)
    @NotNull(message = "Address cannot be null")
    private Address address;
    @Size(min = 10, max = 10, message = "Mobile must be 10 characters")
    private String mobile;
    @Size(min = 10, max = 10, message = "Alternate Mobile must be 10 characters")
    private String alternateMobile;
    @Size(min = 1, max = 50, message = "Email cannot be more than 50 characters")
    @Email(message = "Email should be valid")
    private String email;
    @Size(min = 10, max = 10, message = "Whatsapp mobile must be 10 characters")
    private String whatsapp;
}
