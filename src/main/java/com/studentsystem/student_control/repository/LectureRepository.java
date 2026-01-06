package com.studentsystem.student_control.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.studentsystem.student_control.model.Lecture;
import java.util.List;

public interface LectureRepository extends JpaRepository<Lecture, Long> {

    List<Lecture> findByClassRoomId(long classRoomId);
}
