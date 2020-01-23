package com.suceava.proiect.tema3.service;

import com.suceava.proiect.tema3.entity.TestEntity;

import java.util.List;

public interface TestService {
    List<TestEntity> getTestEntity();
    TestEntity createTestEntity(TestEntity testEntity);
}
