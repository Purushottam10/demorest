package com.dz.service;

import com.dz.model.Student;

import java.util.List;

public interface StudentService {
    Student getStudentByRollNo(int rollNo);

    void saveStudent(Student student);

    void updateStudent(Student student);

    void deleteStudentById(Student student);

    List<Student> findAllStudent();


}
