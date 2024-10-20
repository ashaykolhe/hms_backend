package com.kolhe.hms.service;

import com.kolhe.hms.dto.RegistrationDto;
import com.kolhe.hms.exception.EmailAlreadyExistsException;
import com.kolhe.hms.exception.MobileAlreadyExistsException;
import com.kolhe.hms.exception.UserNameAlreadyTakenException;
import com.kolhe.hms.model.User;
import com.kolhe.hms.model.tenant.ContactInformation;
import com.kolhe.hms.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
public class IRegistrationServiceImpl implements IRegistrationService {

    static StringBuilder stringBuilder = new StringBuilder();
    private final UserService userService;
    private final UserRepository userRepository;

    public IRegistrationServiceImpl(UserService userService, UserRepository userRepository) {
        Objects.requireNonNull(userService, "userService cannot be null");
        Objects.requireNonNull(userRepository, "userRepository cannot be null");
        this.userRepository = userRepository;
        this.userService = userService;
    }

    @Override
    public User register(RegistrationDto registrationDto) {
        Optional<User> byUserName = userRepository.findByUserName(registrationDto.getUserName());
        if (byUserName.isPresent()) {
            throw new UserNameAlreadyTakenException("User name already taken.");
        }
        Optional<User> byEmail = userRepository.findByContactInformation_Email(registrationDto.getEmail());
        if (byEmail.isPresent()) {
            throw new EmailAlreadyExistsException("Email already exists.");
        }
        Optional<User> byContactInformationMobile = userRepository.findByContactInformation_Mobile(registrationDto.getMobile());
        if (byContactInformationMobile.isPresent()) {
            throw new MobileAlreadyExistsException("Mobile already exists.");
        }
        ContactInformation contactInformation = new ContactInformation();
        contactInformation.setEmail(registrationDto.getEmail());
        contactInformation.setMobileCountryCode(registrationDto.getMobileCountryCode());
        contactInformation.setMobile(registrationDto.getMobile());
        contactInformation.setWhatsapp(registrationDto.getWhatsapp());
        User user = User.builder()
                .userName(registrationDto.getUserName())
                .middleName(registrationDto.getMiddleName())
                .firstName(registrationDto.getFirstName())
                .lastName(registrationDto.getLastName())
                .contactInformation(contactInformation)
                .fullName(stringBuilder
                        .append(registrationDto.getFirstName())
                        .append(" ")
                        .append(registrationDto.getMiddleName())
                        .append(" ")
                        .append(registrationDto.getLastName())
                        .toString())
                .password(registrationDto.getPassword())
                .role(registrationDto.getRole())
                .build();
        return userService.saveUser(user);
    }
}
