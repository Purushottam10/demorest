package com.dz.controller;


import com.dz.model.Student;
import com.dz.service.StudentService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;


@RestController
public class StudentController {

    private final Logger log = LogManager.getLogger(StudentController.class.getName());

    private StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {

        this.studentService = studentService;
    }

    /**
     *
     * this is method return the student List to the json format
     * @return studentList
     */
    @RequestMapping(value = "/")
    public ResponseEntity<List<Student>> getStudent() {
        List<Student> studentList = studentService.findAllStudent();
        try {
            for (Student stu : studentList) {
                log.info(stu.getRollNO() + stu.getName() + "controller");
            }
            return new ResponseEntity<>(studentList, HttpStatus.OK);

        } catch (NullPointerException e) {
            log.error(e.getMessage(), e);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

    }

    /**
     * to Add  a  new Student
     */
     @RequestMapping(value = "/student",method = RequestMethod.POST)
        public ResponseEntity<Void> addStudent(@RequestBody Student student, UriComponentsBuilder uriComponentsBuilder){
            log.info(student.getRollNO()+"  "+student.getName()+" "+student.getAge() +"during adding data");
         studentService.saveStudent(student);
         HttpHeaders headers=new HttpHeaders();
         headers.setLocation(uriComponentsBuilder.path("/student/{rollNo}").buildAndExpand(student.getRollNO()).toUri());
         return new ResponseEntity<>(headers,HttpStatus.CREATED);
     }

}

