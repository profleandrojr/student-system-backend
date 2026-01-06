package com.studentsystem.student_control.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.studentsystem.student_control.dto.TopicDTO;
import com.studentsystem.student_control.model.Topic;
import com.studentsystem.student_control.service.TopicService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/topics")
@RequiredArgsConstructor
public class TopicController {

    private final TopicService topicService;

    @PostMapping
    public ResponseEntity<Topic> create(@RequestBody TopicDTO dto) {
        return ResponseEntity.ok(topicService.createTopic(dto));
    }

    @org.springframework.web.bind.annotation.PutMapping("/{id}")
    public ResponseEntity<com.studentsystem.student_control.model.Topic> update(@PathVariable Long id, @RequestBody com.studentsystem.student_control.dto.TopicDTO dto) {
        return ResponseEntity.ok(topicService.updateTopic(id, dto));
    }

    @org.springframework.web.bind.annotation.DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        topicService.deleteTopic(id);
        return ResponseEntity.noContent().build();
    }

}
