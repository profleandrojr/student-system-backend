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

    public com.studentsystem.student_control.model.Topic updateTopic(Long id, com.studentsystem.student_control.dto.TopicDTO dto) {
        var topic = topicRepository.findById(id)
                .orElseThrow(() -> new jakarta.persistence.EntityNotFoundException("Topic not found"));

        // Update fields
        topic.setName(dto.name());
        topic.setDescription(dto.description());
        // Ideally we check if teacher exists before switching, but for speed:
        if (dto.teacherId() != null) {
            var teacher = teacherRepository.findById(dto.teacherId())
                    .orElseThrow(() -> new jakarta.persistence.EntityNotFoundException("Teacher not found"));
            topic.setTeacher(teacher);
        }

        return topicRepository.save(topic);
    }

    public void deleteTopic(Long id) {
        if (!topicRepository.existsById(id)) {
            throw new jakarta.persistence.EntityNotFoundException("Topic not found");
        }
        topicRepository.deleteById(id);
    }

}
