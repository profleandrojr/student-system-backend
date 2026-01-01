package com.studentsystem.student_control.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.studentsystem.student_control.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

    // SELECT * FROM users WHERE email = ?
    Optional<User> findByEmail(String email);

    boolean existsByEmail(String email);
}
