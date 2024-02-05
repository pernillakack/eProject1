package com.perni.eProject1;

import com.perni.eProject1.user.UserEntity;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import static com.perni.eProject1.user.Roles.ADMIN;

@SpringBootApplication
public class EProject1Application {

	public static void main(String[] args) {
		SpringApplication.run(EProject1Application.class, args);

		UserEntity userEntity = new UserEntity();
		userEntity.setRole(ADMIN);

		System.out.println("DEBUGGING "+ ADMIN.name());
		System.out.println("DEBUGGING #2 " + userEntity.getAuthorities());
	}

}
