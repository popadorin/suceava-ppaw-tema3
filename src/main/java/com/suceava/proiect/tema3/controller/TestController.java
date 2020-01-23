package com.suceava.proiect.tema3.controller;

import com.suceava.proiect.tema3.entity.TestEntity;
import com.suceava.proiect.tema3.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/test")
public class TestController {
    private final TestService testService;

    @Autowired
    public TestController(final TestService testService) {
        this.testService = testService;
    }

    @GetMapping
    public String start() {
        return "index";
    }

    @GetMapping("/list")
    public String list(Model model) {
        List<TestEntity> testEntities = this.testService.getTestEntity();
        model.addAttribute("testEntities", testEntities);
        return "testEntities";
    }

    @GetMapping("/add")
    public String addTestEntityShow(TestEntity testEntity) {
        return "addTestEntity";
    }

    @PostMapping("/add")
    public String save(TestEntity testEntity, Model model) {
        System.out.println("TEST ENTT: " + testEntity);
        this.testService.createTestEntity(testEntity);
        model.addAttribute("te", new TestEntity(testEntity.getName()));
        return "addTestEntity";
    }
}
