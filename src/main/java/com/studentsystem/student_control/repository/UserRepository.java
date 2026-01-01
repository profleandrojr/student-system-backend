package com.studentsystem.student_control.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.studentsystem.student_control.model.User;

import graphql.com.google.common.base.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    // SELECT * FROM users WHERE email = ?
    Optional<User> findByEmail(String email);

    boolean existsByEmail(String email);
}
