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
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;

    public List<Student> getAllByGenderAndAgeAndCourse_University(GenderEnum gender, String university, int youngerThan) {
        Course course = Course.builder()
                .university(university)
                .build();
        Student student = Student.builder()
                .gender(gender)
                .course(course)
                .build();

        return studentRepository.findAllByGenderAndAgeAndCourse_University(student, youngerThan);
    }

    public Student addStudent(String firstName, String lastName, LocalDate birthDate,
                              GenderEnum gender, Course course) {
        Student student = Student.builder()
                .firstName(firstName)
                .lastName(lastName)
                .birthDate(birthDate)
                .gender(gender)
                .course(course)
                .build();
        return studentRepository.save(student);

    }


    public List<Student> getAllByLastName(String lastName) {
        return studentRepository.findAllByLastName(lastName);
    }

    public Optional<Student> getStudentByGenderAndByCourseType(GenderEnum gender, CourseTypeEnum type) {
        return studentRepository.findStudentByGenderAndCourse_Type(gender, type);
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }


    public boolean deleteStudentById(Long id) {
        Optional<Student> student = studentRepository.findById(id);
        if (student.isPresent()) {
            studentRepository.deleteById(id);
            return true;
        }
        return false;
    }

}
