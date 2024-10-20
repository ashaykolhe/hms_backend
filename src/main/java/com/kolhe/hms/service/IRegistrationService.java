package com.kolhe.hms.service;

import com.kolhe.hms.dto.RegistrationDto;
import com.kolhe.hms.model.User;

public interface IRegistrationService {
    User register(RegistrationDto registrationDto);
}
