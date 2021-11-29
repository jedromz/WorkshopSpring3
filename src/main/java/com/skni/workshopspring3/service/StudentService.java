package com.skni.workshopspring3.service;

import com.skni.workshopspring3.repository.StudentRepository;
import com.skni.workshopspring3.repository.model.Course;
import com.skni.workshopspring3.repository.model.CourseTypeEnum;
import com.skni.workshopspring3.repository.model.GenderEnum;
import com.skni.workshopspring3.repository.model.Student;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.bytebuddy.description.type.TypeDescription;
import org.springframework.stereotype.Service;

import javax.crypto.spec.OAEPParameterSpec;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;


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

    public List<Student> findAllByLastName(String lastName) {
        return studentRepository.findAllByFirstName(lastName);
    }

    public Optional<Student> getStudentByGenderAndByCourseType(GenderEnum gender, CourseTypeEnum type) {
        return studentRepository.findStudentByGenderAndCourse_Type(gender, type);
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }


}
