package com.suceava.proiect.tema3.repository;

import com.suceava.proiect.tema3.entity.Subject;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubjectRepository extends CrudRepository<Subject, Long> {
    @Override
    List<Subject> findAll();
}
