package com.kolhe.hms.service;

import com.kolhe.hms.exception.UserNotFoundException;
import com.kolhe.hms.model.User;

public interface IUserService {
    User findByUserName(String userName) throws UserNotFoundException;
    User saveUser(User user);
}
