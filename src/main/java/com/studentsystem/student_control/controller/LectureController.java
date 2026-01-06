package com.studentsystem.student_control.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.studentsystem.student_control.dto.LectureDTO;
import com.studentsystem.student_control.dto.LectureResponseDTO;
import com.studentsystem.student_control.service.LectureService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/lectures")
@RequiredArgsConstructor
public class LectureController {

    private final LectureService lectureService;

    @PostMapping
    public ResponseEntity<LectureResponseDTO> create(@RequestBody LectureDTO dto) {
        return ResponseEntity.ok(lectureService.createLecture(dto));
    }

    @org.springframework.web.bind.annotation.PutMapping("/{id}")
    public ResponseEntity<com.studentsystem.student_control.dto.LectureResponseDTO> update(@PathVariable Long id, @RequestBody com.studentsystem.student_control.dto.LectureDTO dto) {
        return ResponseEntity.ok(lectureService.updateLecture(id, dto));
    }

    @org.springframework.web.bind.annotation.DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        lectureService.deleteLecture(id);
        return ResponseEntity.noContent().build();
    }
}
