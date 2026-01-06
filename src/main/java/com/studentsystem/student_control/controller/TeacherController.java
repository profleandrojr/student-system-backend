package com.studentsystem.student_control.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
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

    @org.springframework.web.bind.annotation.PutMapping("/{id}")
    public ResponseEntity<Teacher> update(@PathVariable Long id, @RequestBody com.studentsystem.student_control.dto.TeacherUpdateDTO dto) {
        return ResponseEntity.ok(teacherService.updateTeacher(id, dto));
    }

    @org.springframework.web.bind.annotation.DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        teacherService.deleteTeacher(id);
        return ResponseEntity.noContent().build();
    }

}
