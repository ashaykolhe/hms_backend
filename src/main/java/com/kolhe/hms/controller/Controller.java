package com.kolhe.hms.controller;

import com.kolhe.hms.model.User;
import com.kolhe.hms.model.tenant.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

@RestController
public class Controller {

    @RequestMapping("/")
    public User returnUserJson() {
        User user = new User();
        ContactInformation contactInformation = new ContactInformation();
        Address address = new Address();
        contactInformation.setAddress(new Address());
        user.setContactInformation(contactInformation);
        user.setIdentityDocuments(new IdentityDocuments());

        FamilyInfo familyInfo = new FamilyInfo();
        ContactInformation contactInformation1 = new ContactInformation();
        contactInformation1.setAddress(new Address());
        familyInfo.setContactInformation(contactInformation1);
        PersonalInfo personalInfo = new PersonalInfo();
//        familyInfo.setPersonalInfo(personalInfo);
        personalInfo.setFamilyInfoList(Arrays.asList(familyInfo));
        user.setPersonalInfo(personalInfo);
        return user;
    }
}