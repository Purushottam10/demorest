package com.dz.service.impl;

import com.dz.dao.StudentDao;
import com.dz.model.Student;
import com.dz.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

import java.util.List;


@Service
@Transactional
public class StudentServiceImpl implements StudentService {

    private final StudentDao studentDao;

    @Autowired
    public StudentServiceImpl(StudentDao studentDao) {
        this.studentDao = studentDao;
    }

    @Override
    public Student findByRollNo(int rollNo) {
        return null;
    }

    @Override
    public Student findByName(String name) {
        return null;
    }

    @Override
    public void saveStudent(Student student) {

    }

    @Override
    public void updateStudent(Student student) {

    }

    @Override
    public void deleteStudentById(int rollNo) {

    }

    @Override
    public List<Student> findAllStudent() {
        return studentDao.findAllStudent();
    }

    @Override
    public void deleteAllStudent() {

    }

    @Override
    public boolean isStudentExist(Student student) {
        return false;
    }

}
