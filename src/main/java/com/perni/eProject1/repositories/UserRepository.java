package com.perni.eProject1.repositories;

import com.perni.eProject1.models.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity,String> {

    UserEntity findByEmail(String email);
}
