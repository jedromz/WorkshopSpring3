package com.skni.workshopspring3.dto;

import com.skni.workshopspring3.repository.model.GenderEnum;
import lombok.Data;

import java.time.LocalDate;

@Data
public class StudentResponse {

    private String firstName;
    private String lastName;
    private LocalDate birthDate;
    private GenderEnum gender;
    private CourseResponse course;

}
