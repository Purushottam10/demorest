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

    private  StudentDao studentDao;

    @Autowired
    public StudentServiceImpl(StudentDao studentDao) {
        this.studentDao = studentDao;
    }

    /**
     *
     * @param rollNo get the Student by their rollNo
     * @return Student Object
     * call the Dao method getStudentByRollNo
     */
    @Override
    public Student getStudentByRollNo(int rollNo) {
        return studentDao.getStudentByRollNo(rollNo);
    }

    /**
     *
     * @param student to save new Student call the Dao Class Method saveStudent
     */
    @Override
    public void saveStudent(Student student) {
     studentDao.saveStudent(student);
    }

    /**
     *
     * @param student update the Existing Record
     *
     */
    @Override
    public void updateStudent(Student student) {
        studentDao.updateStudent(student);

    }

    /**
     *
     * @param student to Delete the single Student from the data Base
     */
    @Override
    public void deleteStudentById(Student student) {
      studentDao.deleteStudentById(student);
    }

    /**
     *
     * @return studentList from data base
     */
    @Override
    public List<Student> findAllStudent() {
        return studentDao.findAllStudent();
    }

}
