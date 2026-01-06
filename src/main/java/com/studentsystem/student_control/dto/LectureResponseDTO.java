package com.studentsystem.student_control.dto;

import java.time.LocalDateTime;

public record LectureResponseDTO(
        Long id,
        String title,
        LocalDateTime startTime,
        LocalDateTime endTime,
        boolean active,
        String classRoomName
        ) {

}
