package com.example.Learning_System.serviceImpl;

import com.example.Learning_System.entity.Course;
import com.example.Learning_System.entity.Enrollment;
import com.example.Learning_System.entity.User;
import com.example.Learning_System.repository.CourseRepository;
import com.example.Learning_System.repository.EnrollmentRepository;
import com.example.Learning_System.repository.UserRepository;
import com.example.Learning_System.service.EnrollmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnrollmentServiceImpl implements EnrollmentService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private EnrollmentRepository enrollmentRepository;

    @Override
    public String enrollStudent(Long studentId, Long courseId) {

        User student = userRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course not found"));

        Enrollment e = new Enrollment();
        e.setStudent(student);
        e.setCourse(course);

        enrollmentRepository.save(e);

        return "Student enrolled successfully";
    }

    public List<Course> getCoursesOfStudent(Long studentId) {

        List<Enrollment> enrollments = enrollmentRepository.findByStudentId(studentId);

        return enrollments.stream()
                .map(Enrollment::getCourse)
                .toList();
    }

}

