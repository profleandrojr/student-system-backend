package com.studentsystem.student_control.service;

import org.springframework.stereotype.Service;

import com.studentsystem.student_control.dto.LectureDTO;
import com.studentsystem.student_control.dto.LectureResponseDTO;
import com.studentsystem.student_control.model.Lecture;
import com.studentsystem.student_control.repository.ClassRoomRepository;
import com.studentsystem.student_control.repository.LectureRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LectureService {

    private final LectureRepository lectureRepository;
    private final ClassRoomRepository classRoomRepository;

    public LectureResponseDTO createLecture(LectureDTO dto) {
        var classRoom = classRoomRepository.findById(dto.classRoomId())
                .orElseThrow(() -> new EntityNotFoundException("ClassRoom not found"));

        // FIX: Calculate default end time (Start + 1 Hour)
        var calculatedEndTime = dto.startTime().plusHours(1);

        var lecture = Lecture.builder()
                .title(dto.title())
                .startTime(dto.startTime())
                .endTime(calculatedEndTime) // <--- Set the calculated value here
                .classRoom(classRoom)
                .active(true)
                .build();

        Lecture saved = lectureRepository.save(lecture);

        return new LectureResponseDTO(
                saved.getId(),
                saved.getTitle(),
                saved.getStartTime(),
                saved.getEndTime(),
                saved.isActive(),
                saved.getClassRoom().getName()
        );
    }
}
