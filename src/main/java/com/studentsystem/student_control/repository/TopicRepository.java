package com.studentsystem.student_control.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.studentsystem.student_control.model.Topic;

public interface TopicRepository extends JpaRepository<Topic, Long> {

}
