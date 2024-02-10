package com.perni.eProject1.config;

import com.perni.eProject1.services.UserEntityDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

import static com.perni.eProject1.models.Roles.ADMIN;
import static com.perni.eProject1.models.Roles.USER;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final PasswordConfig passwordConfig;
    private final UserEntityDetailsService userEntityDetailsService;

    @Autowired
    public SecurityConfig(PasswordConfig passwordConfig, UserEntityDetailsService userEntityDetailsService) {
        this.passwordConfig = passwordConfig;
        this.userEntityDetailsService = userEntityDetailsService;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        return http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/", "/register", "/access-denied").permitAll()
                        .requestMatchers("/user", "/todo", "/create-todo", "/delete/**", "/edit/**").hasAnyRole(ADMIN.name(), USER.name())
                        .requestMatchers("/admin").hasRole(ADMIN.name())
                        .anyRequest().permitAll()

                )
                .formLogin(formLogin -> formLogin
                        .loginPage("/login")
                        .usernameParameter("email")
                        .defaultSuccessUrl("/user")
                )
                .exceptionHandling(access -> access
                        .accessDeniedPage("/access-denied"))
                .authenticationProvider(daoAuthenticationProvider())
                .build();

    }


    public DaoAuthenticationProvider daoAuthenticationProvider(){
        DaoAuthenticationProvider daoProvider = new DaoAuthenticationProvider();

        daoProvider.setPasswordEncoder(passwordConfig.bCryptPasswordEncoder());
        daoProvider.setUserDetailsService(userEntityDetailsService);

        return daoProvider;
    }
}
