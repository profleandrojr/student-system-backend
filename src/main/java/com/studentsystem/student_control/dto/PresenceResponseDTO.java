package com.studentsystem.student_control.dto;

import java.time.LocalDateTime;

public record PresenceResponseDTO(Long id,
        String studentName,
        String lectureTitle,
        LocalDateTime checkInTime) {

}
