package com.kolhe.hms.service;

import com.kolhe.hms.exception.UserNotFoundException;
import com.kolhe.hms.model.User;
import com.kolhe.hms.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
public class UserService implements IUserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        Objects.requireNonNull(userRepository, "userRepository cannot be null");
        this.userRepository = userRepository;
    }

    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User findByUserName(String userName) throws UserNotFoundException {
        Optional<User> user = userRepository.findByUserName(userName);
        return user.orElseThrow(() -> new UserNotFoundException("User not found."));
    }

    @Override
    public User findByEmail(String email) throws UserNotFoundException {
        Optional<User> user = userRepository.findByContactInformation_Email(email);
        return user.orElseThrow(() -> new UserNotFoundException("User not found."));
    }

    @Override
    public User findByMobile(String mobile) throws UserNotFoundException {
        Optional<User> user = userRepository.findByContactInformation_Mobile(mobile);
        return user.orElseThrow(() -> new UserNotFoundException("User not found."));
    }
}
