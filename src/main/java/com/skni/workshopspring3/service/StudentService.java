package com.skni.workshopspring3.service;

import com.skni.workshopspring3.repository.StudentRepository;
import com.skni.workshopspring3.repository.model.Course;
import com.skni.workshopspring3.repository.model.CourseTypeEnum;
import com.skni.workshopspring3.repository.model.GenderEnum;
import com.skni.workshopspring3.repository.model.Student;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Slf4j
@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;


    public Student addStudent(String anna, String kowalska, LocalDate of, GenderEnum female, Course course) {
        return new Student();
    }

    public boolean findAllByLastName(String nowak) {
        return true;
    }

    public boolean getStudentByGenderAndByCourseType(GenderEnum male, CourseTypeEnum inzynier) {

        return true;
    }

    public boolean getAllStudents() {
        return true;
    }

    public boolean deleteStudentById(Long id) {
        return true;
    }
}
