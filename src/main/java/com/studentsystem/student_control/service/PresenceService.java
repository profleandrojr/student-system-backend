package com.studentsystem.student_control.service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.studentsystem.student_control.dto.PresenceDTO;
import com.studentsystem.student_control.dto.PresenceResponseDTO;
import com.studentsystem.student_control.model.Presence;
import com.studentsystem.student_control.model.Student;
import com.studentsystem.student_control.repository.LectureRepository;
import com.studentsystem.student_control.repository.PresenceRepository;
import com.studentsystem.student_control.repository.UserRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PresenceService {

    private final PresenceRepository presenceRepository;
    private final LectureRepository lectureRepository;
    private final UserRepository userRepository;

    public PresenceResponseDTO checkIn(PresenceDTO dto) {
        // Fetch Lecture
        var lecture = lectureRepository.findById(dto.lectureId())
                .orElseThrow(() -> new EntityNotFoundException("Lecture not found"));

        //  Validate Lecture is Active
        if (!lecture.isActive()) {
            throw new IllegalStateException("Lecture is not active");
        }

        // Fetch Student
        var studentUser = userRepository.findById(dto.studentId())
                .orElseThrow(() -> new EntityNotFoundException("Student not found"));

        if (!(studentUser instanceof Student student)) {
            throw new IllegalArgumentException("User is not a student");
        }

        //  CRITICAL: Check if Student is Enrolled in the ClassRoom
        // We look at the ClassRoom associated with this Lecture
        boolean isEnrolled = lecture.getClassRoom().getStudents().contains(student);
        if (!isEnrolled) {
            throw new IllegalStateException("Student is not enrolled in this ClassRoom");
        }

        // Prevent Duplicate Check-in
        if (presenceRepository.existsByLectureIdAndStudentId(dto.lectureId(), dto.studentId())) {
            throw new IllegalStateException("Student already checked in");
        }

        // Save Presence
        var presence = Presence.builder()
                .lecture(lecture)
                .student(student)
                .checkInTime(LocalDateTime.now())
                .build();

        Presence saved = presenceRepository.save(presence);

        // Return DTO
        return new PresenceResponseDTO(
                saved.getId(),
                student.getFirstName() + " " + student.getLastName(),
                lecture.getTitle(),
                saved.getCheckInTime()
        );
    }
}
