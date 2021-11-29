package com.skni.workshopspring3.repository;

import com.skni.workshopspring3.repository.model.CourseTypeEnum;
import com.skni.workshopspring3.repository.model.GenderEnum;
import com.skni.workshopspring3.repository.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Long> {


    List<Student> findAllByLastName(String lastName);

    Optional<Student> findStudentByGenderAndCourse_Type(GenderEnum gender, CourseTypeEnum course_type);

    //    @Query(
//            value = "SELECT distinct p.* FROM person p JOIN address a ON p.address_id = a.id WHERE p.gender = ?1 " +
//                    "AND a.country = ?2",
//            nativeQuery = true)
    @Query(
            value = "SELECT distinct s.* FROM student s JOIN course c ON s.course_id = c.id WHERE s.gender = ?1 " +
                    "AND c.university = ?2",
            nativeQuery = true)
    List<Student> findAllByGenderAndAgeAndUniversity(GenderEnum genderEnum,  String university);
}
