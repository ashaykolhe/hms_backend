package com.kolhe.hms.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.kolhe.hms.model.dms.Document;
import com.kolhe.hms.model.tenant.ContactInformation;
import com.kolhe.hms.model.tenant.EnumRole;
import com.kolhe.hms.model.tenant.IdentityDocuments;
import com.kolhe.hms.model.tenant.PersonalInfo;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
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
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "contact_information_id")
    @NotNull
    @Valid
    private ContactInformation contactInformation;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "identity_documents_id")
    @Valid
    private IdentityDocuments identityDocuments;
    @Enumerated(EnumType.STRING)
    @NotNull
    private EnumRole role;
    @Size(min = 5, max = 20, message = "User name must be unique and between 5 and 20 characters")
    @Column(unique = true, nullable = false)
    private String userName;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
//    @JsonIgnore
    @Column(nullable = false)
    private String password;
    @Min(value = 0, message = "Age cannot be less than 0")
    private Integer age;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "personal_info_id")
    private PersonalInfo personalInfo;
    @OneToMany(mappedBy = "createdBy")
    private List<Document> uploadedDocuments;
}
