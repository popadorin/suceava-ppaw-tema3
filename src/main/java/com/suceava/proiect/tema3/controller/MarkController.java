package com.suceava.proiect.tema3.controller;

import com.suceava.proiect.tema3.entity.Mark;
import com.suceava.proiect.tema3.entity.Student;
import com.suceava.proiect.tema3.entity.Subject;
import com.suceava.proiect.tema3.helper.MarkHelper;
import com.suceava.proiect.tema3.service.MarkService;
import com.suceava.proiect.tema3.service.StudentService;
import com.suceava.proiect.tema3.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
public class MarkController {
    private final MarkService markService;
    private final StudentService studentService;
    private final SubjectService subjectService;

    @Autowired
    public MarkController(
            final MarkService markService,
            final StudentService studentService,
            final SubjectService subjectService
    ) {
        this.markService = markService;
        this.studentService = studentService;
        this.subjectService = subjectService;
    }

    @GetMapping("enterMarks")
    public String startForm(Mark mark, Model model) {
        List<Student> students = this.studentService.findAll();
        List<Subject> subjects = this.subjectService.findAll();
        model.addAttribute("students", students);
        model.addAttribute("subjects", subjects);
        return "pages/enterMarks";
    }

    @PostMapping("enterMarks")
    public String saveMark(@Valid @ModelAttribute("mark") Mark mark, Errors errors, Model model) {
        if (errors.hasErrors()) {
            return "pages/enterMarks";
        }
        this.markService.saveMark(mark);
        String studentName = mark.getStudent().getFullName();
        String subjectName = mark.getSubject().getName();
        int studentMark = mark.getValue();
        String markAddResult = String.format("Student %s has %s mark at %s", studentName, studentMark, subjectName);
        model.addAttribute("markAddResult", markAddResult);
        List<Student> students = this.studentService.findAll();
        List<Subject> subjects = this.subjectService.findAll();
        model.addAttribute("subjects", subjects);
        model.addAttribute("students", students);

        return "pages/enterMarks";
    }

    @GetMapping("showMarks")
    public String showMarks(Model model) {
        List<Subject> subjects = this.subjectService.findAll();
        List<Mark> marks = this.markService.getMarks();
        List<String> tableHeaders = MarkHelper.getTableHeaders(subjects);
        Map<Student, List<Integer>> tableMarks = MarkHelper.getTableMarks(marks, subjects);
        System.out.println(tableHeaders);
        System.out.println(tableMarks);

        model.addAttribute("tableHeaders", tableHeaders);
        model.addAttribute("tableMarks", tableMarks);

        return "pages/showMarks";
    }

    @GetMapping("showMarksPerSubject")
    public String showMarksPerSubject(Model model) {
        List<Subject> subjects = this.subjectService.findAll();
        model.addAttribute("subjects", subjects);

        return "pages/showMarksPerSubject";
    }

    @PostMapping("showMarksPerSubject")
    public String selectSubject(Subject subject, Model model) {
        List<Mark> marks = this.markService.getMarks();
        Map<Student, Integer> selectedMarks = MarkHelper.getStudentMarks(marks, subject);

        model.addAttribute("marks", selectedMarks);
        List<Subject> subjects = this.subjectService.findAll();
        model.addAttribute("subjects", subjects);

        return "pages/showMarksPerSubject";
    }

    @GetMapping("showMarksPerStudent")
    public String showMarksPerStudent(Model model) {
        List<Student> students = this.studentService.findAll();
        model.addAttribute("students", students);

        return "pages/showMarksPerStudent";
    }

    @PostMapping("showMarksPerStudent")
    public String selectStudent(Student student, Model model) {
        List<Mark> marks = this.markService.getMarks();
        Map<Subject, Integer> selectedMarks = MarkHelper.getSubjectMarks(marks, student);
        model.addAttribute("marks", selectedMarks);
        List<Student> students = this.studentService.findAll();
        model.addAttribute("students", students);

        return "pages/showMarksPerStudent";
    }
}
