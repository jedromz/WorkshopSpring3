package com.skni.workshopspring3.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import com.skni.workshopspring3.repository.StudentRepository;
import com.skni.workshopspring3.repository.model.Course;
import com.skni.workshopspring3.repository.model.CourseTypeEnum;
import com.skni.workshopspring3.repository.model.GenderEnum;
import com.skni.workshopspring3.repository.model.Student;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;
    private final ObjectMapper objectMapper;

    public Optional<Student> getStudentById(long id) {
        return studentRepository.findById(id);
    }

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

    public Student addStudent(Student student) {
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

    //nie chcialem sprawdziac wszystkich atrybutow to znalazlem cos takiego
    //https://www.baeldung.com/spring-rest-json-patch
    public Student applyPatchToStudent(
            JsonPatch patch, Student targetStudent) throws JsonPatchException, JsonProcessingException {
        JsonNode patched = patch.apply(objectMapper.convertValue(targetStudent, JsonNode.class));
        return objectMapper.treeToValue(patched, Student.class);
    }

    public boolean updateStudent(long id, Student newStudent) {
        Optional<Student> currentStudent = studentRepository.findById(id);
        if (currentStudent.isPresent()) {
            Student student = currentStudent.get();
            newStudent.setId(id);
            newStudent.setCourse(student.getCourse());
            studentRepository.save(newStudent);
            return true;
        }
        return false;

    }


}
