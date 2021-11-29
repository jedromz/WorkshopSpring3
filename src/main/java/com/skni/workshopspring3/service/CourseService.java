package com.skni.workshopspring3.service;

import com.skni.workshopspring3.repository.CourseRepository;
import com.skni.workshopspring3.repository.model.Course;
import com.skni.workshopspring3.repository.model.CourseTypeEnum;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class CourseService {

    private final CourseRepository courseRepository;


    public Course addCourse(String name, int term, String university, CourseTypeEnum courseTypeEnum) {
        Course course = Course.builder()
                .name(name)
                .term(term)
                .university(university)
                .courseTypeEnum(courseTypeEnum).build();
        return courseRepository.save(course);
    }
}
