package com.skni.workshopspring3.dto;

import com.skni.workshopspring3.repository.model.CourseTypeEnum;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class CourseRequest {
    @NotBlank(message = "First name is mandatory")
    @Size(min = 3, max = 20)
    private String name;
    @NotBlank(message = "First name is mandatory")
    private int term;
    @NotBlank(message = "First name is mandatory")
    @Size(min = 3, max = 20)
    private String university;
    @NotBlank
    private CourseTypeEnum type;
}
