package com.example.twitter.repos;

import com.example.twitter.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.jws.soap.SOAPBinding;

public interface UserRepo extends JpaRepository<User, Long> {
    User findByUsername(String username);
    User findByActivationCode(String code);
}
