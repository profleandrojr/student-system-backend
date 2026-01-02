package com.studentsystem.student_control.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.studentsystem.student_control.model.ClassRoom;

public interface ClassRoomRepository extends JpaRepository<ClassRoom, Long> {

}
