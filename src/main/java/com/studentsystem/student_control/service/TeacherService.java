package com.studentsystem.student_control.service;

import org.springframework.stereotype.Service;

import com.studentsystem.student_control.dto.TeacherRegistrationDTO;
import com.studentsystem.student_control.model.Role;
import com.studentsystem.student_control.model.Teacher;
import com.studentsystem.student_control.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TeacherService {

    private final UserRepository userRepository;

    public Teacher registerTeacher(TeacherRegistrationDTO dto) {
        var teacher = Teacher.builder()
                .firstName(dto.firstName())
                .lastName(dto.lastName())
                .email(dto.email())
                .password(dto.password()) // TODO: Encrypt later
                .role(Role.TEACHER)
                .department(dto.department())
                .academicTitle(dto.academicTitle())
                .build();

        return userRepository.save(teacher);
    }

}
