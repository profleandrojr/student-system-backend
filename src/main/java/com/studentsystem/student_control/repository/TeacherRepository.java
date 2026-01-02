package com.studentsystem.student_control.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.studentsystem.student_control.model.Teacher;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {

}
