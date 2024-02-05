package com.perni.eProject1.controllers;

import com.perni.eProject1.config.PasswordConfig;
import com.perni.eProject1.repositories.UserRepository;
import com.perni.eProject1.user.Roles;
import com.perni.eProject1.user.UserEntity;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import static com.perni.eProject1.user.Roles.USER;

@Controller
public class UserController {

    private final UserRepository userRepository;
    private final PasswordConfig passwordConfig;

    public UserController(UserRepository userRepository, PasswordConfig passwordConfig) {
        this.userRepository = userRepository;
        this.passwordConfig = passwordConfig;
    }
    @GetMapping("/register")
    public String registerUserPage(UserEntity userEntity, Model model){

        model.addAttribute("roles", Roles.values());
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@Valid UserEntity userEntity, BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            return "register";
        }
        userEntity.setPassword(passwordConfig.bCryptPasswordEncoder().encode(userEntity.getPassword()));
        userEntity.setAccountEnabled(true);
        userEntity.setAccountNonLocked(true);
        userEntity.setAccountNonExpired(true);
        userEntity.setCredentialsNonExpired(true);
        userEntity.getAuthorities();

        userRepository.save(userEntity);

        return "redirect:user";
    }
}
