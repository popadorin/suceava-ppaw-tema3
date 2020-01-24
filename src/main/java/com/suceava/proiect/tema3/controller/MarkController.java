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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

        return "pages/enterMarks";
    }

    @GetMapping("showMarks")
    public String showMarks(Mark mark, Model model) {
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
    public String showMarksPerSubject(Subject subject, Model model) {
        List<Subject> subjects = this.subjectService.findAll();
        model.addAttribute("subjects", subjects);

        return "pages/showMarksPerSubject";
    }

    @PostMapping("showMarksPerSubject")
    public String selectSubject(@Valid @RequestParam("subject") Subject subject, Errors errors, Model model) {
        if (errors.hasErrors()) {
            return "pages/showMarksPerSubject";
        }

        Subject selectedSubject = new Subject("OOP");
        List<Mark> marks = this.markService.getMarks();
        List<Mark> selectedMarks = marks.stream()
                .filter(m -> m.getSubject().equals(selectedSubject))
                .collect(Collectors.toList());

        System.out.println(subject);
        model.addAttribute("marks", selectedMarks);

        return "pages/showMarksPerSubject";
    }

    @GetMapping("showMarksPerStudent")
    public String showMarksPerStudent(Student s, Model model) {
        List<Student> students = this.studentService.findAll();
        model.addAttribute("students", students);

        return "pages/showMarksPerStudent";
    }

    @PostMapping("showMarksPerStudent")
    public String selectStudent(Model model, @RequestParam("s") Student student) {

        Student selectedStudent = new Student("grisha", "petrovici");
        List<Mark> marks = this.markService.getMarks();
        List<Mark> selectedMarks = marks.stream()
                .filter(m -> m.getStudent().equals(selectedStudent))
                .collect(Collectors.toList());

        System.out.println(student);
        model.addAttribute("marks", selectedMarks);

        return "pages/showMarksPerStudent";
    }
}
