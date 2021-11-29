package com.skni.workshopspring3.repository;

import com.skni.workshopspring3.repository.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
}
