package com.example.Learning_System.repository;

import com.example.Learning_System.entity.Course;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {

    Page<Course> findByTitleContainingIgnoreCase(String title, Pageable pageable);
}
