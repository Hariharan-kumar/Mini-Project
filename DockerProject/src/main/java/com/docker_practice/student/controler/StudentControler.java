package com.docker_practice.student.controler;

import com.docker_practice.student.domain.Students;
import com.docker_practice.student.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/student")
public class StudentControler {

    @Autowired
    private StudentService service;

    @PostMapping("/add")
    public Students addStudent(Students student){
        return service.addStudent(student);
    }
    @GetMapping("/get/{id}")
    public Students getStudentById(@PathVariable String id){
        return service.getStudentById(id);
    }
    @DeleteMapping("/delete/{id}")
    public void deleteStudentById(String id){
        service.deleteStudentById(id);
    }
    @PutMapping("/update")
    public Students updateStudent(Students student){
        return service.updateStudent(student);
    }
    @GetMapping("/all")
    public java.util.List<Students> getAllStudents(){
        return service.getAllStudents();
    }

}
