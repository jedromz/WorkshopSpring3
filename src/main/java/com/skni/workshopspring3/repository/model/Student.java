package com.skni.workshopspring3.repository.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Student {
    private Long id;
    private String firstName;
    private String lastName;
    private LocalDate birthDate;
    private GenderEnum gender;

}
