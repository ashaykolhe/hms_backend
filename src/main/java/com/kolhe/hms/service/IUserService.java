package com.kolhe.hms.service;

import com.kolhe.hms.dto.RegistrationDto;
import com.kolhe.hms.exception.UserNotFoundException;
import com.kolhe.hms.model.User;

public interface IUserService {
    User findByUserName(String userName) throws UserNotFoundException;
    User findByEmail(String email) throws UserNotFoundException;
    User saveUser(User user);
    User findByMobile(String mobile) throws UserNotFoundException;
}
