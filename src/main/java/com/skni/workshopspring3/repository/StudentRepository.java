package com.skni.workshopspring3.repository;

import com.skni.workshopspring3.repository.model.CourseTypeEnum;
import com.skni.workshopspring3.repository.model.GenderEnum;
import com.skni.workshopspring3.repository.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Long> {


    List<Student> findAllByLastName(String lastName);

    Optional<Student> findStudentByGenderAndCourse_Type(GenderEnum gender, CourseTypeEnum course_type);
}
