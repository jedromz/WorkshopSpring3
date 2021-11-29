package com.skni.workshopspring3.repository;

import com.skni.workshopspring3.repository.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {


    findAllByFirstName(String lastName);
}
