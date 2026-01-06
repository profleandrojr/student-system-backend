package com.studentsystem.student_control.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.studentsystem.student_control.dto.GradeDTO;
import com.studentsystem.student_control.dto.GradeResponseDTO;
import com.studentsystem.student_control.service.GradeService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/grades")
@RequiredArgsConstructor
public class GradeController {

    private final GradeService gradeService;

    // Assign a grade to a student
    @PostMapping
    public ResponseEntity<GradeResponseDTO> assignGrade(@RequestBody GradeDTO dto) {
        return ResponseEntity.ok(gradeService.assignGrade(dto));
    }

    // Get all grades for a specific student
    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<GradeResponseDTO>> getStudentGrades(@PathVariable Long studentId) {
        return ResponseEntity.ok(gradeService.getGradesByStudent(studentId));
    }

    // Get all grades for a specific class
    @GetMapping("/class/{classId}")
    public ResponseEntity<List<GradeResponseDTO>> getClassGrades(@PathVariable Long classId) {
        return ResponseEntity.ok(gradeService.getGradesByClass(classId));
    }

    @org.springframework.web.bind.annotation.PutMapping("/{id}")
    public ResponseEntity<GradeResponseDTO> update(@PathVariable Long id, @RequestBody com.studentsystem.student_control.dto.GradeDTO dto) {
        return ResponseEntity.ok(gradeService.updateGrade(id, dto));
    }

    @org.springframework.web.bind.annotation.DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        gradeService.deleteGrade(id);
        return ResponseEntity.noContent().build();
    }

}
