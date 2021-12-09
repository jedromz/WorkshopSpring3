package com.skni.workshopspring3.dto;

import com.skni.workshopspring3.repository.model.GenderEnum;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Data
public class StudentRequest {

    @NotBlank(message = "First name is mandatory")
    @Size(min = 3, max = 20)
    private String firstName;
    @NotBlank(message = "Last name is mandatory")
    @Size(min = 3, max = 20)
    private String lastName;
    @NotBlank(message = "Birthdate is mandatory")
    private LocalDate birthDate;
    @NotBlank(message = "Gender is mandatory")
    private GenderEnum gender;

}
