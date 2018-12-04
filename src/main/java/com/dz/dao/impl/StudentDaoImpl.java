package com.dz.dao.impl;

import com.dz.dao.StudentDao;
import com.dz.model.Student;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Repository
public class StudentDaoImpl implements StudentDao {

    private final Logger log = LogManager.getLogger(StudentDao.class.getName());
    private final SessionFactory sessionFactory;

    @Autowired
    public StudentDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
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
        try {
            Query query = sessionFactory.openSession().createQuery(" From Student");
            List<Student> studentList = query.list();
           for(Student stu:studentList){
               log.info(stu.getRollNO()+stu.getName());
           }
            return studentList;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }

        return null;

    }

    @Override
    public void deleteAllStudent() {

    }

    @Override
    public boolean isStudentExist(Student student) {
        return false;
    }
}
