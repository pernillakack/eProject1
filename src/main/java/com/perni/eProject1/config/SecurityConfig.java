package com.perni.eProject1.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import static com.perni.eProject1.user.Roles.ADMIN;
import static com.perni.eProject1.user.Roles.USER;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        return http
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/").permitAll()
                        .requestMatchers("/register").permitAll()
                        .requestMatchers("/user").hasAnyRole(ADMIN.name(), USER.name())
                        .requestMatchers("/admin").hasRole(ADMIN.name())
                        .anyRequest().authenticated()
                )
                .formLogin(Customizer.withDefaults())
                .build();

    }
    @Bean
    public UserDetailsService userDetailsService(){
        UserDetails user = User.withDefaultPasswordEncoder()
                .username("Penny")
                .password("1234")
                .roles(USER.name())
                .build();

        UserDetails admin = User.withDefaultPasswordEncoder()
                .username("Admin")
                .password("12345")
                .roles(ADMIN.name())
                .build();

        return new InMemoryUserDetailsManager(user, admin);
    }

}
