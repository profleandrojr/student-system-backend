package com.studentsystem.student_control.dto;

public record GradeDTO(
        Double score,
        String description,
        Long studentId,
        Long classRoomId) {

}
