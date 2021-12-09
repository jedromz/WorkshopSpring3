package com.skni.workshopspring3.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import com.skni.workshopspring3.repository.CourseRepository;
import com.skni.workshopspring3.repository.model.Course;
import com.skni.workshopspring3.repository.model.CourseTypeEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class CourseService {

    private final CourseRepository courseRepository;
    private final ObjectMapper objectMapper;

    public Course addCourse(String name, int term, String university, CourseTypeEnum courseTypeEnum) {
        Course course = Course.builder()
                .name(name)
                .term(term)
                .university(university)
                .type(courseTypeEnum).build();
        return courseRepository.save(course);
    }

    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    public Optional<Course> getCourseById(long id) {
        return courseRepository.findById(id);
    }

    public Course addCourse(Course course) {
        return courseRepository.save(course);
    }

    public boolean deleteCourse(Long id) {
        Optional<Course> course = courseRepository.findById(id);
        if (course.isPresent()) {
            courseRepository.deleteById(id);
            return true;
        }
        return false;
    }

    //nie chcialem sprawdziac wszystkich atrybutow to znalazlem cos takiego
    //https://www.baeldung.com/spring-rest-json-patch
    public Course applyPatchToCourse(
            JsonPatch patch, Course targetCourse) throws JsonPatchException, JsonProcessingException {
        JsonNode patched = patch.apply(objectMapper.convertValue(targetCourse, JsonNode.class));
        return objectMapper.treeToValue(patched, Course.class);
    }

    public boolean updateCourse(long id, Course newCourse) {
        Optional<Course> currentCourse = courseRepository.findById(id);
        if (currentCourse.isPresent()) {
            Course course = currentCourse.get();
            newCourse.setId(id);
            courseRepository.save(newCourse);
            return true;
        }
        return false;

    }
}
