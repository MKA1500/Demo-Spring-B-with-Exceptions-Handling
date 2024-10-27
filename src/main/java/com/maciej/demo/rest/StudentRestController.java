package com.maciej.demo.rest;

import com.maciej.demo.entity.Student;
import jakarta.annotation.PostConstruct;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class StudentRestController {
  List<Student> theStudents;

  // @PostConstruct works only once...
  @PostConstruct
  public void init() {
    theStudents = new ArrayList<>();
    theStudents.add(new Student("Maciej", "Kabsch"));
    theStudents.add(new Student("Marian", "Swirski"));
    theStudents.add(new Student("Aleksander", "Wyrwal"));
    theStudents.add(new Student("Tomasz", "Lis"));
    theStudents.add(new Student("Agata", "Ogorek"));
    theStudents.add(new Student("Lukasz", "Ogorek"));
  }

  @GetMapping("/students")
  public List<Student> getStudents() {
    return theStudents;
  }

  @GetMapping("/students/{studentId}")
  public Student getStudentById(@PathVariable int studentId) {
    if ((studentId >= theStudents.size()) || (studentId < 0)) {
      throw new StudentNotFoundException("Student not found with id " + studentId);
    }

    return theStudents.get(studentId);
  }

  @ExceptionHandler
  public ResponseEntity<StudentErrorResponse> handleNotFoundException(StudentNotFoundException e) {
    StudentErrorResponse errorResponse = new StudentErrorResponse();
    errorResponse.setStatus(HttpStatus.NOT_FOUND.value());
    errorResponse.setMessage(e.getMessage());
    errorResponse.setTimeStamp(System.currentTimeMillis());

    return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler
  public ResponseEntity<StudentErrorResponse> handleOtherExceptions(Exception e) {
    StudentErrorResponse errorResponse = new StudentErrorResponse();
    errorResponse.setStatus(HttpStatus.BAD_REQUEST.value());
    errorResponse.setMessage(e.getMessage());
    errorResponse.setTimeStamp(System.currentTimeMillis());

    return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
  }
}






















