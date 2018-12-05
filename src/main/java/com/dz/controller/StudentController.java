package com.dz.controller;


import com.dz.model.Student;
import com.dz.service.StudentService;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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

    /**
     * to update the existing record only name and age
     * @param student to set the record in new student object
     * @return update student Record
     */
    @RequestMapping(value = "/updatestudent", method = RequestMethod.PUT)
    public ResponseEntity<Student> updateStudent(@RequestBody Student student) {

        log.info("update the record " + student.getAge() + " " + student.getName() + "  " + student.getRollNO());

        try {
            studentService.updateStudent(student);
            return new ResponseEntity(student, HttpStatus.OK);
        } catch (Exception e) {
            log.error(e.getMessage());
            return new ResponseEntity("not Data found ", HttpStatus.NO_CONTENT);
        }
    }
/**
 *  delete a record to database
 * @param student
 */
@RequestMapping(value = "/deletestudent", method = RequestMethod.DELETE)
public ResponseEntity<Student> deleStudent(@RequestBody Student student) {
    try {
        studentService.deleteStudentById(student);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    } catch (Exception e) {
        log.error(e.getMessage(), e);
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }
}
/**
 * select single student from data base
 * @param rollNo to select the Student by their RollNo
 */
@RequestMapping(value = "/getstudent-by-id/{rollNo}", method = RequestMethod.GET)
    public ResponseEntity<Student> getStudentById(@PathVariable("rollNo") int rollNo){
      try{
          Student student=studentService.getStudentByRollNo(rollNo);
          return new ResponseEntity(student,HttpStatus.OK);

      }catch (Exception e){
          log.error(e.getMessage(),e);
          return new ResponseEntity("No Data Mathched in Database",HttpStatus.NOT_FOUND);
      }
    }
}
