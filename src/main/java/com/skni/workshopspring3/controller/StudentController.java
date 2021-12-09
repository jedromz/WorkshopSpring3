package com.skni.workshopspring3.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import com.skni.workshopspring3.dto.StudentRequest;
import com.skni.workshopspring3.dto.StudentResponse;
import com.skni.workshopspring3.repository.StudentRepository;
import com.skni.workshopspring3.repository.model.Student;
import com.skni.workshopspring3.service.StudentService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
public class StudentController {

    private final StudentService studentService;
    private final ModelMapper modelMapper;
    private final StudentRepository studentRepository;

    @GetMapping("/students")
    public List<StudentResponse> getStudents() {
        return Optional.ofNullable(studentService.getAllStudents())
                .orElse(Collections.emptyList())
                .stream()
                .filter(Objects::nonNull)
                .map(s -> modelMapper.map(s, StudentResponse.class))
                .collect(Collectors.toList());
    }

    @GetMapping("/students/{id}")
    public ResponseEntity<StudentResponse> getStudent(@PathVariable long id) {
        Optional<Student> studentOptional = studentService.getStudentById(id);
        return studentOptional
                .map(student -> modelMapper.map(student, StudentResponse.class))
                .map(student -> new ResponseEntity<>(student, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/students")
    public ResponseEntity<StudentResponse> addStudent(@Valid @RequestBody StudentRequest newStudent) {
        Student student = modelMapper.map(newStudent, Student.class);
        studentService.addStudent(student);
        StudentResponse newStudentResponse = modelMapper.map(student, StudentResponse.class);
        return new ResponseEntity<>(newStudentResponse, HttpStatus.CREATED);
    }

    @PutMapping(path = "/students/{id}")
    public ResponseEntity<?> updateStudent(@PathVariable long id, @RequestBody StudentRequest studentRequest) {

        Student newStudent = modelMapper.map(studentRequest, Student.class);
        studentService.updateStudent(id, newStudent);
        return ResponseEntity.ok(newStudent);
    }

    //nie chcialem sprawdziac wszystkich atrybutow to znalazlem cos takiego
    //https://www.baeldung.com/spring-rest-json-patch

    @PatchMapping(path = "/students/{id}", consumes = "application/json-patch+json")
    public ResponseEntity<Student> patchStudent(@PathVariable long id, @RequestBody JsonPatch patch) {
        try {
            Student student = studentService.getStudentById(id).get();
            Student studentPatched = studentService.applyPatchToStudent(patch, student);
            studentRepository.save(studentPatched);
            return ResponseEntity.ok(studentPatched);
        } catch (JsonPatchException | JsonProcessingException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/students/{id}")
    public ResponseEntity<?> deleteStudent(@PathVariable long id) {
        studentService.deleteStudentById(id);
        return ResponseEntity.noContent().build();
    }

    // https://www.baeldung.com/spring-boot-bean-validation
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }

}
