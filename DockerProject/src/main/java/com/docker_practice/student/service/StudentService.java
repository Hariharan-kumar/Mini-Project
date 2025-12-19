package com.docker_practice.student.service;

import com.docker_practice.student.dao.StudentRepo;
import com.docker_practice.student.domain.Students;
import com.docker_practice.student.exceptionhandler.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    @Autowired
    private StudentRepo repo;


    public Students addStudent(Students student) {
        return repo.save(student);
    }
    public Students getStudentById(String id) {
        return repo.findById(id).orElseThrow(()-> new UserNotFoundException("Student with id "+id+" not found"));
    }
    public void deleteStudentById(String id) {
        repo.deleteById(id);
    }
    public Students updateStudent(Students student) {
        return repo.save(student);
    }
    public List<Students> getAllStudents() {
        return repo.findAll();
    }
}
