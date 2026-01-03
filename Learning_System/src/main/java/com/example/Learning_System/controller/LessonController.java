package com.example.Learning_System.controller;

import com.example.Learning_System.entity.Course;
import com.example.Learning_System.entity.Lesson;
import com.example.Learning_System.repository.CourseRepository;
import com.example.Learning_System.repository.LessonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/lessons")
public class LessonController {

    @Autowired
    private LessonRepository lessonRepository;

    @Autowired
    private CourseRepository courseRepository;

    @PostMapping("/course/{courseId}")
    public Lesson addLesson(@PathVariable Long courseId, @RequestBody Lesson lesson) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course not found"));
        lesson.setCourse(course);
        return lessonRepository.save(lesson);
    }

    @GetMapping("/course/{courseId}")
    public List<Lesson> getLessons(@PathVariable Long courseId) {
        return lessonRepository.findAll()
                .stream()
                .filter(l -> l.getCourse().getId().equals(courseId))
                .toList();
    }
}

