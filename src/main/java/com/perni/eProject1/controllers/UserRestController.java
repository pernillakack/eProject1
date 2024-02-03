package com.perni.eProject1.controllers;

import com.perni.eProject1.config.PasswordConfig;
import com.perni.eProject1.repositories.UserRepository;
import com.perni.eProject1.user.Roles;
import com.perni.eProject1.user.UserEntity;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;
import org.slf4j.Logger;


@RestController
@RequestMapping("/")
public class UserRestController {
    private final Logger logger = (Logger) LoggerFactory.getLogger(UserRestController.class);

    private final PasswordConfig passwordConfig;
    private final UserRepository userRepository;

    @Autowired
    public UserRestController(PasswordConfig passwordConfig, UserRepository userRepository) {
        this.passwordConfig = passwordConfig;
        this.userRepository = userRepository;
    }

    @PostMapping("/register")
    public ResponseEntity<UserEntity> registerNewUser(@RequestBody UserEntity newUser){
        logger.info("Received registration request: {}"+newUser);
        UserEntity userEntity = new UserEntity(
                newUser.getEmail(),
                passwordConfig.bCryptPasswordEncoder().encode(newUser.getPassword()),
                newUser.isAccountNonExpired(),
                newUser.isEnabled(),
                newUser.isAccountNonLocked(),
                newUser.isCredentialsNonExpired()
        );


        return new ResponseEntity<>(userRepository.save(userEntity), HttpStatus.CREATED);
    }

}
