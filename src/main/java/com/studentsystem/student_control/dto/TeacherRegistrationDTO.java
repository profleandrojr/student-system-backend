package com.studentsystem.student_control.dto;

public record TeacherRegistrationDTO(
        String firstName,
        String lastName,
        String email,
        String password,
        String department,
        String academicTitle
        ) {

}
