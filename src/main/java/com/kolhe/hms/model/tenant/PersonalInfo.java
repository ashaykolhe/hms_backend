package com.kolhe.hms.model.tenant;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonalInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Boolean isMarried;
    private Boolean pets;
    @OneToMany(mappedBy = "personalInfo", cascade = CascadeType.ALL)
    private List<FamilyInfo> familyInfoList;
}
