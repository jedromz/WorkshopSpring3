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


    public Course addCourse(String informatyka, int i, String sgh, CourseTypeEnum inzynier) {
        return new Course();
    }
}
