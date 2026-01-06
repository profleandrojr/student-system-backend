package com.studentsystem.student_control.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.studentsystem.student_control.dto.StudentRegistrationDTO;
import com.studentsystem.student_control.dto.StudentResponseDTO;
import com.studentsystem.student_control.model.Student;
import com.studentsystem.student_control.service.StudentService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/students")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @PostMapping
    public ResponseEntity<Student> register(@RequestBody StudentRegistrationDTO dto) {
        return ResponseEntity.ok(studentService.registerStudent(dto));
    }

    @GetMapping
    public ResponseEntity<List<StudentResponseDTO>> getAll() {
        return ResponseEntity.ok(studentService.getAllStudents());
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentResponseDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(studentService.getStudentById(id));
    }

    @org.springframework.web.bind.annotation.PutMapping("/{id}")
    public ResponseEntity<StudentResponseDTO> update(@PathVariable Long id, @RequestBody com.studentsystem.student_control.dto.StudentUpdateDTO dto) {
        return ResponseEntity.ok(studentService.updateStudent(id, dto));
    }

    @org.springframework.web.bind.annotation.DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        studentService.deleteStudent(id);
        return ResponseEntity.noContent().build();
    }

}
