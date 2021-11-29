package com.skni.workshopspring3.repository.model;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.Period;

@Entity
@Table(name = "student")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    @Column(name = "firstName")
    private String firstName;
    @Column(name = "lastName")
    private String lastName;
    @Column(name = "birthDate")
    private LocalDate birthDate;
    @Enumerated(EnumType.STRING)
    @Column(name = "gender")
    private GenderEnum gender;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "course_id", nullable = false)
    private Course course;

    public int wiek() {
        return Period.between(birthDate, LocalDate.now()).getYears();
    }

}
