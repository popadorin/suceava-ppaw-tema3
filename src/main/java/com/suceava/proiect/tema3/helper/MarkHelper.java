package com.suceava.proiect.tema3.helper;

import com.suceava.proiect.tema3.entity.Mark;
import com.suceava.proiect.tema3.entity.Student;
import com.suceava.proiect.tema3.entity.Subject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MarkHelper {
    public static List<String> getTableHeaders(List<Subject> subjects) {
        List<String> tableHeaders = new ArrayList<>();
        tableHeaders.add("Student");
        subjects.forEach(s -> tableHeaders.add(s.getName()));

        return tableHeaders;
    }

    public static Map<Student, List<Integer>> getTableMarks(List<Mark> marks, List<Subject> subjects) {
        Map<Student, Map<Subject, Integer>> tableMarks = new HashMap<>();

        marks.forEach(m -> {
            if (tableMarks.containsKey(m.getStudent())) {
                int average = m.getValue();
                Integer value = tableMarks.get(m.getStudent()).get(m.getSubject());
                if (value != null) {
                    average = (m.getValue() + value) / 2;
                }
                tableMarks.get(m.getStudent()).put(m.getSubject(), average);
            } else {
                Map<Subject, Integer> subjectAndValues = new HashMap<>();
                subjects.forEach(subject -> subjectAndValues
                        .put(subject, subject == m.getSubject() ? m.getValue() : null)
                );
                tableMarks.put(m.getStudent(), subjectAndValues);
            }
        });

        Map<Student, List<Integer>> orderedMarks = new HashMap<>();
        tableMarks.keySet().forEach(student -> {
            Map<Subject, Integer> subjectIntegerMap = tableMarks.get(student);

            List<Integer> list = new ArrayList<>();
            subjects.forEach(s -> list.add(subjectIntegerMap.get(s)));

            orderedMarks.put(student, list);
        });

        return orderedMarks;
    }

    public static Map<Student, Integer> getStudentMarks(List<Mark> marks, Subject subject) {
        Map<Student, Integer> studentMarks = new HashMap<>();
        List<Mark> selectedMarks = marks.stream()
                .filter(m -> m.getSubject().equals(subject))
                .collect(Collectors.toList());

        selectedMarks.forEach(sm -> {
            Student s = sm.getStudent();
            if (studentMarks.containsKey(s)) {
                Integer value = studentMarks.get(s);
                Integer average = (value + sm.getValue()) / 2;
                studentMarks.put(s, average);
            } else {
                studentMarks.put(sm.getStudent(), sm.getValue());
            }
        });

        return studentMarks;
    }

    public static Map<Subject, Integer> getSubjectMarks(List<Mark> marks, Student student) {
        Map<Subject, Integer> studentMarks = new HashMap<>();
        List<Mark> selectedMarks = marks.stream()
                .filter(m -> m.getStudent().equals(student))
                .collect(Collectors.toList());

        selectedMarks.forEach(sm -> {
            Subject s = sm.getSubject();
            if (studentMarks.containsKey(s)) {
                Integer value = studentMarks.get(s);
                Integer average = (value + sm.getValue()) / 2;
                studentMarks.put(s, average);
            } else {
                studentMarks.put(sm.getSubject(), sm.getValue());
            }
        });

        return studentMarks;
    }
}
