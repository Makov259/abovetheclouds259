package com.simpleform.repository;

import com.simpleform.entity.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsersRepository extends JpaRepository<UserModel, Integer> {
    Optional<UserModel> findByLoginAndPassword(String email, String password);
}
