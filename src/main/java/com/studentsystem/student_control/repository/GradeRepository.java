package com.studentsystem.student_control.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.studentsystem.student_control.model.Grade;

public interface GradeRepository extends JpaRepository<Grade, Long> {

    // Enable fetching all grades for a specific student
    List<Grade> findByStudentId(Long studentId);

    // Enable fetching all grades for a specific class
    List<Grade> findByClassRoomId(Long classRoomId);
}
