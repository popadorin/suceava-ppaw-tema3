package com.suceava.proiect.tema3.service;

import com.suceava.proiect.tema3.entity.Mark;

import java.util.List;

public interface MarkService {
    List<Mark> getMarks();
    Mark saveMark(Mark mark);
}
