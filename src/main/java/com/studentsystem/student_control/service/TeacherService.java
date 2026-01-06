package com.studentsystem.student_control.service;

import org.springframework.stereotype.Service;

import com.studentsystem.student_control.dto.TeacherRegistrationDTO;
import com.studentsystem.student_control.model.Role;
import com.studentsystem.student_control.model.Teacher;
import com.studentsystem.student_control.repository.UserRepository;
import com.studentsystem.student_control.repository.TeacherRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TeacherService {

    private final UserRepository userRepository;
    private final TeacherRepository teacherRepository;

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

    public Teacher updateTeacher(Long id, com.studentsystem.student_control.dto.TeacherUpdateDTO dto) {
        var teacher = teacherRepository.findById(id)
                .orElseThrow(() -> new jakarta.persistence.EntityNotFoundException("Teacher not found"));

        teacher.setFirstName(dto.firstName());
        teacher.setLastName(dto.lastName());
        teacher.setEmail(dto.email());
        teacher.setDepartment(dto.department());
        teacher.setAcademicTitle(dto.academicTitle());

        return teacherRepository.save(teacher);
    }

    public void deleteTeacher(Long id) {
        if (!teacherRepository.existsById(id)) {
            throw new jakarta.persistence.EntityNotFoundException("Teacher not found");
        }
        teacherRepository.deleteById(id);
    }

}
