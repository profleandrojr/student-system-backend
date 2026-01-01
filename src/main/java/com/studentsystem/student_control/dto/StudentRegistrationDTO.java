package com.studentsystem.student_control.dto;

public record StudentRegistrationDTO(
        String firstName,
        String lastName,
        String email,
        String password,
        String matriculationNumber
        ) {

}
