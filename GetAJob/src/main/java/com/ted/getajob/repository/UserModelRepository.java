package com.ted.getajob.repository;

import com.ted.getajob.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserModelRepository extends JpaRepository<UserModel, Long> {
    UserModel findByUsername(String username);

    UserModel findByEmail(String email);
}
