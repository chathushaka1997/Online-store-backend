package com.example.demo.repository;

import com.example.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query(value = "select * from user",nativeQuery = true)
    Optional<List<User>> getAllUsers();

    Optional<User> findByName(String name);
    Boolean existsByName(String name);
}