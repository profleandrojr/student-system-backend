package com.studentsystem.student_control.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.studentsystem.student_control.dto.StudentRegistrationDTO;
import com.studentsystem.student_control.dto.StudentResponseDTO;
import com.studentsystem.student_control.dto.StudentUpdateDTO;
import com.studentsystem.student_control.model.Role;
import com.studentsystem.student_control.model.Student;
import com.studentsystem.student_control.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final UserRepository userRepository;

    public Student registerStudent(StudentRegistrationDTO dto) {

        var student = Student.builder()
                .firstName(dto.firstName())
                .lastName(dto.lastName())
                .email(dto.email())
                .password(dto.password()) // TODO: Add BCrypt encryption later
                .role(Role.STUDENT) // Explicitly setting the role
                .matriculationNumber(dto.matriculationNumber())
                .build();

        return userRepository.save(student);
    }

    public List<StudentResponseDTO> getAllStudents() {
        return userRepository.findAll().stream()
                // Filter only Students (since users table has Teachers too)
                .filter(user -> user instanceof Student)
                .map(user -> (Student) user)
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    public StudentResponseDTO getStudentById(Long id) {
        return userRepository.findById(id)
                .filter(user -> user instanceof Student)
                .map(user -> (Student) user)
                .map(this::mapToResponse)
                .orElseThrow(() -> new jakarta.persistence.EntityNotFoundException("Student not found"));
    }

    private StudentResponseDTO mapToResponse(Student student) {
        return new StudentResponseDTO(
                student.getId(),
                student.getFirstName(),
                student.getLastName(),
                student.getEmail(),
                student.getMatriculationNumber()
        );
    }

    public StudentResponseDTO updateStudent(Long id, StudentUpdateDTO dto) {
        var student = userRepository.findById(id)
                .filter(user -> user instanceof Student)
                .map(user -> (Student) user)
                .orElseThrow(() -> new jakarta.persistence.EntityNotFoundException("Student not found"));

        // Update fields
        student.setFirstName(dto.firstName());
        student.setLastName(dto.lastName());
        student.setEmail(dto.email());
        student.setMatriculationNumber(dto.matriculationNumber());

        return mapToResponse(userRepository.save(student));
    }

    public void deleteStudent(Long id) {
        if (!userRepository.existsById(id)) {
            throw new jakarta.persistence.EntityNotFoundException("Student not found");
        }
        userRepository.deleteById(id);
    }

}
