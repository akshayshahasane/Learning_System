package com.example.Learning_System.repository;

import com.example.Learning_System.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {
}
