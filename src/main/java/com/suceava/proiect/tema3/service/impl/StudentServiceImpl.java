package com.suceava.proiect.tema3.service.impl;

import com.suceava.proiect.tema3.entity.Student;
import com.suceava.proiect.tema3.repository.StudentRepository;
import com.suceava.proiect.tema3.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private final StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public List<Student> findAll() {
        return this.studentRepository.findAll();
    }
}
