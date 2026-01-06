package com.studentsystem.student_control.dto;

public record TeacherUpdateDTO(
        String firstName,
        String lastName,
        String email,
        String department,
        String academicTitle) {

}
