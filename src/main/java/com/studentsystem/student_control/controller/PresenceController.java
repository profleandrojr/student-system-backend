package com.studentsystem.student_control.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.studentsystem.student_control.dto.PresenceDTO;
import com.studentsystem.student_control.dto.PresenceResponseDTO;
import com.studentsystem.student_control.service.PresenceService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/presences")
@RequiredArgsConstructor
public class PresenceController {

    private final PresenceService presenceService;

    @PostMapping("/check-in")
    public ResponseEntity<PresenceResponseDTO> checkIn(@RequestBody PresenceDTO dto) {
        return ResponseEntity.ok(presenceService.checkIn(dto));
    }
}
