package com.perni.eProject1.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry){
        registry.addViewController("/").setViewName("home");
        registry.addViewController("/admin").setViewName("admin");
        registry.addViewController("/register").setViewName("register");
        registry.addViewController("/user").setViewName("user");
        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/create-todo").setViewName("create-todo");
        registry.addViewController("/edit/").setViewName("edit-todo-item");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**")
                .addResourceLocations("classpath:/static/");
    }
}
