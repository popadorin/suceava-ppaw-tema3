package com.suceava.proiect.tema3.repository;

import com.suceava.proiect.tema3.entity.Mark;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MarkRepository extends CrudRepository<Mark, Long> {
    @Override
    List<Mark> findAll();

    @Override
    Mark save(Mark mark);
}
