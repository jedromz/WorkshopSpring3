package com.skni.workshopspring3.repository.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Course {


    private String name;
    private int term;
    private String university;
    private CourseTypeEnum courseTypeEnum;
}
