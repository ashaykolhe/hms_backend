package com.kolhe.hms.controller;

import com.kolhe.hms.exception.UserNotFoundException;
import com.kolhe.hms.model.User;
import com.kolhe.hms.response.AuthResponse;
import com.kolhe.hms.response.ErrorResponse;
import com.kolhe.hms.service.IUserService;
import com.kolhe.hms.service.ValidatorService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final IUserService userService;

    public UserController(IUserService userService) {
        Objects.requireNonNull(userService, "UserService cannot be null");
        this.userService = userService;
    }

    @PostMapping("/create")
    public ResponseEntity<?> createUser(@Valid @RequestBody User user) {
//        ErrorResponse validate = ValidatorService.validate(User.class, user);
//        if(validate != null) {
//            return ResponseEntity.badRequest().body(validate);
//        }
        User user1 = userService.saveUser(user);
        AuthResponse authResponse = new AuthResponse();
        authResponse.setMessage(user1.getId() + " is created successfully.");
        return ResponseEntity.ok(authResponse);
    }

    @GetMapping("/getByUserName/{userName}")
    public ResponseEntity<User> getByUserName(@PathVariable(name = "userName") String userName) throws UserNotFoundException {
        User byUserName = userService.findByUserName(userName);
        return ResponseEntity.ok(byUserName);
    }
}
