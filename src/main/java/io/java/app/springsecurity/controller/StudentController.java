package io.java.app.springsecurity.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.java.app.springsecurity.model.Student;

@RestController
@RequestMapping("api/vi/students")
public class StudentController {
	
	@Value("${hello.name}")
	private String envName;
	
    private final List<Student> students = Arrays.asList(new Student(1, "James Bond"), new Student(2, "Jaden Smith"),
            new Student(3, "Dwayne Johnson"));

    @GetMapping(path = "{studentId}")
    public Student getStudent(@PathVariable("studentId") Integer studentId) {
        return students.stream().filter(students -> studentId.equals(students.getStudentId())).findFirst()
                .orElseThrow(() -> new IllegalStateException("Student " + studentId + "is does not exist"));
    }
    
    
	@GetMapping("test")
	public String getEnv() {
		return envName;
	}
}
