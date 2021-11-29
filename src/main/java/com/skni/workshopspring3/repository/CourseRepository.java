package com.skni.workshopspring3.repository;

import com.skni.workshopspring3.repository.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {
}
