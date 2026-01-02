package com.studentsystem.student_control.dto;

public record ClassRoomResponseDTO(
        Long id,
        String name,
        String topicName,
        String teacherName,
        int numberOfStudents
        ) {

}
