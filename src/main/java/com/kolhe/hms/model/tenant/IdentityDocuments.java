package com.kolhe.hms.model.tenant;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class IdentityDocuments {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(unique = true)
    @Size(min = 12, max = 12, message = "Aadhar number must be of 12 characters")
    private String aadhar;
    @Size(min = 10, max = 10, message = "Pan number must be of 10 characters")
    @Column(unique = true)
    private String pan;
    @Lob
    private byte[] photo;
}
