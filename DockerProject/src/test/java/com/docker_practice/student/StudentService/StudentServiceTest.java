package com.docker_practice.student.StudentService;

import com.docker_practice.student.dao.StudentRepo;
import com.docker_practice.student.domain.Students;
import com.docker_practice.student.service.StudentService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class StudentServiceTest {

    @Mock
    private StudentRepo studentRepo;

    @InjectMocks
    private StudentService studentService;

    @Test
    public void testAddStudent() {
        // Implement test logic here
        Students student = new Students();
        student.setId("1");
        student.setName("John Doe");
        student.setAge(20);
        student.setDepartment("Computer Science");


        Students savedStudent = new Students();




        savedStudent.setId("1");
        savedStudent.setName("John Doe");
        savedStudent.setAge(20);
        savedStudent.setDepartment("Computer Science");

        when(studentRepo.save(student)).thenReturn(savedStudent);


        Students result = studentService.addStudent(student);
        assertEquals(savedStudent.getId(), result.getId());
        assertEquals(savedStudent.getName(), result.getName());
        assertEquals(savedStudent.getAge(), result.getAge());
        assertEquals(savedStudent.getDepartment(), result.getDepartment());
    }
    @Test
    public void getStudentByIdTest(){
        Students student = new Students();
        student.setId("1");
        student.setName("John Doe");
        student.setAge(20);
        student.setDepartment("Computer Science");
        when(studentRepo.findById("1")).thenReturn(java.util.Optional.of(student));

        Students result = studentService.getStudentById("1");
        assertNotEquals(null, result);
        assertEquals(student.getName(), result.getName());
        assertEquals(student.getId(), result.getId());

    }

}
