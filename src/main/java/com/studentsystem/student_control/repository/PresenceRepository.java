package com.studentsystem.student_control.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.studentsystem.student_control.model.Presence;

public interface PresenceRepository extends JpaRepository<Presence, Long> {

    boolean existsByLectureIdAndStudentId(Long lectureId, Long studentId);
}
