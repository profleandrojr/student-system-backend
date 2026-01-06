package com.studentsystem.student_control.dto;

public record StudentResponseDTO(
        Long id,
        String firstName,
        String lastName,
        String email,
        String matriculationNumber) {

}
