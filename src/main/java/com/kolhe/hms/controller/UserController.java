package com.kolhe.hms.controller;

import com.kolhe.hms.dto.RegistrationDto;
import com.kolhe.hms.exception.UserCreationException;
import com.kolhe.hms.exception.UserNotFoundException;
import com.kolhe.hms.model.User;
import com.kolhe.hms.response.AuthResponse;
import com.kolhe.hms.service.IRegistrationService;
import com.kolhe.hms.service.IUserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final IUserService userService;
    private final IRegistrationService registrationService;

    public UserController(IUserService userService, IRegistrationService registrationService) {
        Objects.requireNonNull(userService, "userService cannot be null");
        Objects.requireNonNull(registrationService, "registrationService cannot be null");
        this.registrationService = registrationService;
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@Valid @RequestBody RegistrationDto registrationDto) {
//        ErrorResponse validate = ValidatorService.validate(User.class, user);
//        if(validate != null) {
//            return ResponseEntity.badRequest().body(validate);
//        }
        User createdUser = registrationService.register(registrationDto);
        if(Objects.isNull(createdUser)) {
            throw new UserCreationException("Unable to create user. Please contact owner.");
        }
        AuthResponse authResponse = new AuthResponse();
        authResponse.setMessage(createdUser.getUserName() + " is created successfully.");
        authResponse.setRole(createdUser.getRole());
        return ResponseEntity.ok(authResponse);
    }

    @GetMapping("/getByUserName/{userName}")
    public ResponseEntity<User> getByUserName(@PathVariable(name = "userName") String userName) throws UserNotFoundException {
        User byUserName = userService.findByUserName(userName);
        return ResponseEntity.ok(byUserName);
    }

    @GetMapping("/getByEmail/{email}")
    public ResponseEntity<User> getByEmail(@PathVariable(name = "email") String email) throws UserNotFoundException {
        User byEmail = userService.findByEmail(email);
        return ResponseEntity.ok(byEmail);
    }
}
