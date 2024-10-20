package com.kolhe.hms.repository;

import com.kolhe.hms.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUserName(String userName);
    Optional<User> findByContactInformation_Email(String email);
    Optional<User> findByContactInformation_Mobile(String mobile);
}
