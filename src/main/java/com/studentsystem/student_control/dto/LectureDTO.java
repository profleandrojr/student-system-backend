package com.studentsystem.student_control.dto;

import java.time.LocalDateTime;

public record LectureDTO(
        String title,
        LocalDateTime startTime,
        Long classRoomId
        ) {

}
