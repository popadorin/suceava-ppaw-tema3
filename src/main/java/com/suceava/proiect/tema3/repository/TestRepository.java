package com.suceava.proiect.tema3.repository;

import com.suceava.proiect.tema3.entity.TestEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TestRepository extends CrudRepository<TestEntity, Long> {
    @Override
    List<TestEntity> findAll();

    @Override
    TestEntity save(TestEntity testEntity);
}
