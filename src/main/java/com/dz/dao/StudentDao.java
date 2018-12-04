package com.dz.dao;

import com.dz.model.Student;

import java.util.List;

public interface StudentDao {
    Student findByRollNo(int rollNo);

    Student findByName(String name);

    void saveStudent(Student student);

    void updateStudent(Student student);

    void deleteStudentById(int rollNo);

    List<Student> findAllStudent();

    void deleteAllStudent();

    public boolean isStudentExist(Student student);
}
