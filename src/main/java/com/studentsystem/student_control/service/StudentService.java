package com.studentsystem.student_control.service;

import org.springframework.stereotype.Service;

import com.studentsystem.student_control.dto.StudentRegistrationDTO;
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

}
