package com.studentsystem.student_control.dto;

public record StudentUpdateDTO(
        String firstName,
        String lastName,
        String email,
        String matriculationNumber) {

}
