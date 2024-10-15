package com.kolhe.hms.model.tenant;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class FamilyInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Size(min = 1, max = 20, message = "First name must be between 1 and 20 characters")
    private String firstName;
    @Size(min = 1, max = 20, message = "Middle name must be between 1 and 20 characters")
    private String middleName;
    @Size(min = 1, max = 20, message = "Last name must be between 1 and 20 characters")
    private String lastName;
    @Size(min = 1, max = 60, message = "Full name must be between 1 and 60 characters")
    private String fullName;
    @Min(value = 0, message = "Age cannot be less than 0")
    private Integer age;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "contact_information_id")
    private ContactInformation contactInformation;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "personal_info_id")
    private PersonalInfo personalInfo;
}
