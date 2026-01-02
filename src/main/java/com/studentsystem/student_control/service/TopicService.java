package com.studentsystem.student_control.service;

import org.springframework.stereotype.Service;

import com.studentsystem.student_control.dto.TopicDTO;
import com.studentsystem.student_control.model.Topic;
import com.studentsystem.student_control.repository.TeacherRepository;
import com.studentsystem.student_control.repository.TopicRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TopicService {

    private final TopicRepository topicRepository;
    private final TeacherRepository teacherRepository;

    public Topic createTopic(TopicDTO dto) {

        var teacher = teacherRepository.findById(dto.teacherId())
                .orElseThrow(() -> new EntityNotFoundException("Teacher not found"));

        var topic = Topic.builder()
                .name(dto.name())
                .description(dto.description())
                .teacher(teacher)
                .build();

        return topicRepository.save(topic);
    }

}
