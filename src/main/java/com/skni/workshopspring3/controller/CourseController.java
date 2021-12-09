package com.skni.workshopspring3.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import com.skni.workshopspring3.dto.CourseRequest;
import com.skni.workshopspring3.dto.CourseResponse;
import com.skni.workshopspring3.repository.CourseRepository;
import com.skni.workshopspring3.repository.model.Course;
import com.skni.workshopspring3.service.CourseService;
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
public class CourseController {

    private final CourseService courseService;
    private final ModelMapper modelMapper;
    private final CourseRepository courseRepository;

    @GetMapping("/courses")
    public List<CourseResponse> getCourses() {
        return Optional.ofNullable(courseService.getAllCourses())
                .orElse(Collections.emptyList())
                .stream()
                .filter(Objects::nonNull)
                .map(s -> modelMapper.map(s, CourseResponse.class))
                .collect(Collectors.toList());
    }

    @GetMapping("/courses/{id}")
    public ResponseEntity<CourseResponse> getCourse(@PathVariable long id) {
        Optional<Course> courseOptional = courseService.getCourseById(id);
        return courseOptional
                .map(course -> modelMapper.map(course, CourseResponse.class))
                .map(course -> new ResponseEntity<>(course, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/courses")
    public ResponseEntity<CourseResponse> addCourse(@Valid @RequestBody CourseRequest courseRequest) {
        Course course = modelMapper.map(courseRequest, Course.class);
        courseService.addCourse(course);
        CourseResponse newCourseResponse = modelMapper.map(course, CourseResponse.class);
        return new ResponseEntity<>(newCourseResponse, HttpStatus.CREATED);
    }

    @PutMapping(path = "/courses/{id}")
    public ResponseEntity<?> updateCourse(@PathVariable long id, @RequestBody CourseRequest courseRequest) {

        Course newCourse = modelMapper.map(courseRequest, Course.class);
        courseService.updateCourse(id, newCourse);
        return ResponseEntity.ok(newCourse);
    }

    //nie chcialem sprawdziac wszystkich atrybutow to znalazlem cos takiego
    //https://www.baeldung.com/spring-rest-json-patch

    @PatchMapping(path = "/courses/{id}", consumes = "application/json-patch+json")
    public ResponseEntity<Course> patchCourse(@PathVariable long id, @RequestBody JsonPatch patch) {
        try {
            Course course = courseService.getCourseById(id).get();
            Course coursePatched = courseService.applyPatchToCourse(patch, course);
            courseRepository.save(coursePatched);
            return ResponseEntity.ok(coursePatched);
        } catch (JsonPatchException | JsonProcessingException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/courses/{id}")
    public ResponseEntity<?> deleteCourse(@PathVariable long id) {
        courseService.deleteCourse(id);
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
