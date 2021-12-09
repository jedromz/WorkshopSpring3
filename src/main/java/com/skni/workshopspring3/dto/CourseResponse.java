package com.skni.workshopspring3.dto;

import com.skni.workshopspring3.repository.model.CourseTypeEnum;
import lombok.Data;

@Data
public class CourseResponse {
    private String name;
    private int term;
    private String university;
    private CourseTypeEnum type;
}
