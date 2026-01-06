package com.studentsystem.student_control.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.studentsystem.student_control.dto.GradeDTO;
import com.studentsystem.student_control.dto.GradeResponseDTO;
import com.studentsystem.student_control.model.Grade;
import com.studentsystem.student_control.model.Student;
import com.studentsystem.student_control.repository.ClassRoomRepository;
import com.studentsystem.student_control.repository.GradeRepository;
import com.studentsystem.student_control.repository.UserRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GradeService {

    private final GradeRepository gradeRepository;
    private final ClassRoomRepository classRoomRepository;
    private final UserRepository userRepository;

    public GradeResponseDTO assignGrade(GradeDTO dto) {
        //  Fetch ClassRoom
        var classRoom = classRoomRepository.findById(dto.classRoomId())
                .orElseThrow(() -> new EntityNotFoundException("ClassRoom not found"));

        // Fetch Student
        var studentUser = userRepository.findById(dto.studentId())
                .orElseThrow(() -> new EntityNotFoundException("Student not found"));

        if (!(studentUser instanceof Student student)) {
            throw new IllegalArgumentException("User is not a student");
        }

        //  Validate Enrollment
        if (!classRoom.getStudents().contains(student)) {
            throw new IllegalStateException("Student is not enrolled in this class");
        }

        // Create Grade
        var grade = Grade.builder()
                .score(dto.score())
                .description(dto.description())
                .student(student)
                .classRoom(classRoom)
                .build();

        Grade saved = gradeRepository.save(grade);

        return mapToResponse(saved);
    }

    public List<GradeResponseDTO> getGradesByStudent(Long studentId) {
        return gradeRepository.findByStudentId(studentId).stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    public List<GradeResponseDTO> getGradesByClass(Long classRoomId) {
        return gradeRepository.findByClassRoomId(classRoomId).stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    private GradeResponseDTO mapToResponse(Grade grade) {
        return new GradeResponseDTO(
                grade.getId(),
                grade.getScore(),
                grade.getDescription(),
                grade.getStudent().getFirstName() + " " + grade.getStudent().getLastName(),
                grade.getClassRoom().getName()
        );
    }
}
