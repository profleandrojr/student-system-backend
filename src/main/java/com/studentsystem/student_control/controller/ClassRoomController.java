package com.studentsystem.student_control.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.studentsystem.student_control.dto.ClassRoomDTO;
import com.studentsystem.student_control.dto.ClassRoomResponseDTO; // <--- Import this
import com.studentsystem.student_control.service.ClassRoomService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/classrooms")
@RequiredArgsConstructor
public class ClassRoomController {

    private final ClassRoomService classRoomService;

    // CHANGED: Return type
    @PostMapping
    public ResponseEntity<ClassRoomResponseDTO> create(@RequestBody ClassRoomDTO dto) {
        return ResponseEntity.ok(classRoomService.createClassRoom(dto));
    }

    // CHANGED: Return type
    @PutMapping("/{classId}/students/{studentId}")
    public ResponseEntity<ClassRoomResponseDTO> enroll(
            @PathVariable Long classId,
            @PathVariable Long studentId) {
        return ResponseEntity.ok(classRoomService.enrollStudent(classId, studentId));
    }

    @org.springframework.web.bind.annotation.PutMapping("/{id}")
    public ResponseEntity<ClassRoomResponseDTO> update(@PathVariable Long id, @RequestBody com.studentsystem.student_control.dto.ClassRoomDTO dto) {
        return ResponseEntity.ok(classRoomService.updateClassRoom(id, dto));
    }

    @org.springframework.web.bind.annotation.DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        classRoomService.deleteClassRoom(id);
        return ResponseEntity.noContent().build();
    }
}
