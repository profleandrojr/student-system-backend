package com.studentsystem.student_control.service;

import java.util.HashSet;

import org.springframework.stereotype.Service;

import com.studentsystem.student_control.dto.ClassRoomDTO;
import com.studentsystem.student_control.dto.ClassRoomResponseDTO; // <--- Import this
import com.studentsystem.student_control.model.ClassRoom;
import com.studentsystem.student_control.model.Student;
import com.studentsystem.student_control.repository.ClassRoomRepository;
import com.studentsystem.student_control.repository.TeacherRepository;
import com.studentsystem.student_control.repository.TopicRepository;
import com.studentsystem.student_control.repository.UserRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ClassRoomService {

    private final ClassRoomRepository classRoomRepository;
    private final TeacherRepository teacherRepository;
    private final TopicRepository topicRepository;
    private final UserRepository userRepository;

    // Helper method to convert Entity -> DTO
    private ClassRoomResponseDTO mapToResponse(ClassRoom classRoom) {
        return new ClassRoomResponseDTO(
                classRoom.getId(),
                classRoom.getName(),
                classRoom.getTopic().getName(),
                classRoom.getTeacher().getFirstName() + " " + classRoom.getTeacher().getLastName(),
                classRoom.getStudents().size()
        );
    }

    public ClassRoomResponseDTO createClassRoom(ClassRoomDTO dto) {
        var teacher = teacherRepository.findById(dto.teacherId())
                .orElseThrow(() -> new EntityNotFoundException("Teacher not found"));

        var topic = topicRepository.findById(dto.topicId())
                .orElseThrow(() -> new EntityNotFoundException("Topic not found"));

        var classRoom = ClassRoom.builder()
                .name(dto.name())
                .teacher(teacher)
                .topic(topic)
                .students(new HashSet<>())
                .build();

        ClassRoom saved = classRoomRepository.save(classRoom);
        return mapToResponse(saved); // <--- Return the DTO
    }

    public ClassRoomResponseDTO enrollStudent(Long classId, Long studentId) {
        var classRoom = classRoomRepository.findById(classId)
                .orElseThrow(() -> new EntityNotFoundException("ClassRoom not found"));

        var studentUser = userRepository.findById(studentId)
                .orElseThrow(() -> new EntityNotFoundException("Student not found"));

        if (!(studentUser instanceof Student)) {
            throw new IllegalArgumentException("User is not a student");
        }

        classRoom.getStudents().add((Student) studentUser);

        ClassRoom saved = classRoomRepository.save(classRoom);
        return mapToResponse(saved); // <--- Return the DTO
    }

    public ClassRoomResponseDTO updateClassRoom(Long id, com.studentsystem.student_control.dto.ClassRoomDTO dto) {
        var classRoom = classRoomRepository.findById(id)
                .orElseThrow(() -> new jakarta.persistence.EntityNotFoundException("ClassRoom not found"));

        classRoom.setName(dto.name());
        // Assuming we allow changing topic/teacher (requires fetching them)
        // For brevity, let's just update the name. 
        // If you need to update relations, fetch them like in createClassRoom.

        return mapToResponse(classRoomRepository.save(classRoom));
    }

    public void deleteClassRoom(Long id) {
        if (!classRoomRepository.existsById(id)) {
            throw new jakarta.persistence.EntityNotFoundException("ClassRoom not found");
        }
        classRoomRepository.deleteById(id);
    }

}
