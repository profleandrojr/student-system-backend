package com.studentsystem.student_control.dto;

public record GradeResponseDTO(
        Long id,
        Double score,
        String description,
        String studentName,
        String className) {

}
