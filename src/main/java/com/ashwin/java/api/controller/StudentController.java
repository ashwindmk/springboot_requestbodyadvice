package com.ashwin.java.api.controller;

import com.ashwin.java.domain.model.Student;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/student")
public class StudentController {
    private static final String TAG = StudentController.class.getSimpleName();

    @PostMapping
    @RequestMapping("/reflect")
    public ResponseEntity<Student> reflect(@RequestBody Student student) {
        System.out.println(TAG + " | POST | reflect | Body: " + student);
        return ResponseEntity.ok(student);
    }
}
