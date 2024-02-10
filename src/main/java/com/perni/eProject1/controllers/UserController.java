package com.perni.eProject1.controllers;

import com.perni.eProject1.config.PasswordConfig;
import com.perni.eProject1.models.TodoEntity;
import com.perni.eProject1.repositories.TodoItemRepository;
import com.perni.eProject1.repositories.UserRepository;
import com.perni.eProject1.models.Roles;
import com.perni.eProject1.models.UserEntity;
import jakarta.validation.Valid;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class UserController {

    private final UserRepository userRepository;
    private final TodoItemRepository todoItemRepository;
    private final PasswordConfig passwordConfig;

    public UserController(UserRepository userRepository, TodoItemRepository todoItemRepository, PasswordConfig passwordConfig) {
        this.userRepository = userRepository;
        this.todoItemRepository = todoItemRepository;
        this.passwordConfig = passwordConfig;
    }

    @GetMapping("/access-denied")
    public String accessDenied(){
        return "access-denied";
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
    @GetMapping("/user")
    public String userPage(TodoEntity todoEntity, Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        UserEntity currentUser = userRepository.findByEmail(email);
        List<TodoEntity> userTodos = currentUser.getTodos();
        model.addAttribute("userTodos", userTodos);
        return "user";
    }

}
