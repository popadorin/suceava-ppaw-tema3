package com.suceava.proiect.tema3.service.impl;

import com.suceava.proiect.tema3.entity.Mark;
import com.suceava.proiect.tema3.repository.MarkRepository;
import com.suceava.proiect.tema3.service.MarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MarkServiceImpl implements MarkService {
    @Autowired
    private final MarkRepository markRepository;

    public MarkServiceImpl(MarkRepository markRepository) {
        this.markRepository = markRepository;
    }

    @Override
    public List<Mark> getMarks() {
        return this.markRepository.findAll();
    }

    @Override
    public Mark saveMark(Mark mark) {
        return this.markRepository.save(mark);
    }
}
