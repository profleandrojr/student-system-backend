package com.studentsystem.student_control.controller;

import org.springframework.http.ResponseEntity;
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
}
