package com.suceava.proiect.tema3.service.impl;

import com.suceava.proiect.tema3.entity.Subject;
import com.suceava.proiect.tema3.repository.SubjectRepository;
import com.suceava.proiect.tema3.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubjectServiceImpl implements SubjectService {
    @Autowired
    private final SubjectRepository subjectRepository;

    public SubjectServiceImpl(SubjectRepository subjectRepository) {
        this.subjectRepository = subjectRepository;
    }

    @Override
    public List<Subject> findAll() {
        return this.subjectRepository.findAll();
    }
}
