package com.skni.workshopspring3.repository.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "course")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "term")
    private int term;
    @Column(name = "university")
    private String university;
    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private CourseTypeEnum type;

}
