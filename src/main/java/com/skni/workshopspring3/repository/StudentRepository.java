package com.skni.workshopspring3.repository;

import com.skni.workshopspring3.repository.model.CourseTypeEnum;
import com.skni.workshopspring3.repository.model.GenderEnum;
import com.skni.workshopspring3.repository.model.Student;
import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;


public interface StudentRepository extends JpaRepository<Student, Long> {


    List<Student> findAllByLastName(String lastName);

    Optional<Student> findStudentByGenderAndCourse_Type(GenderEnum gender, CourseTypeEnum course_type);


    //find students younger than youngerThan , by gender and university
    @Query("select distinct s from Student s where s.gender = :#{#student.gender} and  s.course.university = :#{#student.course.university} " +
            "and s.birthDate < dateadd(year, -:youngerThan, curdate())")
    List<Student> findAllByGenderAndAgeAndCourse_University(@Param("student") Student student, @Param("youngerThan") int youngerThan);
}
