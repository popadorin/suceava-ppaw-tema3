package com.suceava.proiect.tema3.repository;

import com.suceava.proiect.tema3.entity.Student;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends CrudRepository<Student, Long> {
    @Override
    List<Student> findAll();
}
