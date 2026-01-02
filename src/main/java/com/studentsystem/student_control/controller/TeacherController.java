package com.studentsystem.student_control.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.studentsystem.student_control.dto.TeacherRegistrationDTO;
import com.studentsystem.student_control.model.Teacher;
import com.studentsystem.student_control.service.TeacherService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/teachers")
@RequiredArgsConstructor
public class TeacherController {

    private final TeacherService teacherService;

    @PostMapping
    public ResponseEntity<Teacher> register(@RequestBody TeacherRegistrationDTO dto) {
        return ResponseEntity.ok(teacherService.registerTeacher(dto));
    }

}
