package com.dz.dao;

import com.dz.model.Student;

import java.util.List;

public interface StudentDao {
    Student getStudentByRollNo(int rollNo);

    void saveStudent(Student student);

    void updateStudent(Student student);

    void deleteStudentById(Student rollNo);

    List<Student> findAllStudent();

}
