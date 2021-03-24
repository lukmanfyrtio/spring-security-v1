package io.java.app.springsecurity.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.java.app.springsecurity.model.Student;

@RestController
@RequestMapping("management/api/vi/students")
public class StudentManagementController {

    private final List<Student> students = Arrays.asList(new Student(1, "James Bond"), new Student(2, "Jaden Smith"),
            new Student(3, "Dwayne Johnson"));
    
    @GetMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_ADMINTRAINE')")
    public List<Student>getAllStudent(){
    	System.out.println("getAllStudent");
    	return students;
    }
    
    @PostMapping
    @PreAuthorize("hasAuthority('student:write')")
    public void registerNewStudent(@RequestBody Student student) {
    	System.out.println("registerNewStudent");
    	System.out.println(student);
    }
    
    @DeleteMapping(path = "{studentId}")
    @PreAuthorize("hasAuthority('student:write')")
    public void deleteStudent(@PathVariable("studentId") Integer studentId) {
    	System.out.println("deleteStudent");
		System.out.println(studentId);
	}
    
    @PutMapping(path = "{studentId}")
    @PreAuthorize("hasAuthority('student:write')")
    public void updateStudent(@PathVariable("studentId") Integer studentId,@RequestBody Student student) {
    	System.out.println("updateStudent");
		System.out.println(String.format("%s %s", studentId,student));
	}
    
}
