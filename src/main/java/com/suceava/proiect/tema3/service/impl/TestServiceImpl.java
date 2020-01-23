package com.suceava.proiect.tema3.service.impl;

import com.suceava.proiect.tema3.entity.TestEntity;
import com.suceava.proiect.tema3.repository.TestRepository;
import com.suceava.proiect.tema3.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class TestServiceImpl implements TestService {
    @Autowired
    private final TestRepository testRepository;

    public TestServiceImpl(TestRepository testRepository) {
        this.testRepository = testRepository;
    }

    @Override
    public List<TestEntity> getTestEntity() {
        List<TestEntity> all = testRepository.findAll();
//        return Arrays.asList(new TestEntity("Jora"), new TestEntity("Grisha"));
        return all;
    }

    @Override
    public TestEntity createTestEntity(TestEntity testEntity) {
//        return testEntity;
        return this.testRepository.save(testEntity);
    }
}
