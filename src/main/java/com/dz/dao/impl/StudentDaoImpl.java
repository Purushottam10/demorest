package com.dz.dao.impl;

import com.dz.dao.StudentDao;
import com.dz.model.Student;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
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

    /**
     * @param rollNo get the Student by their rollNo
     * @return Student Object
     */
    @Override
    public Student getStudentByRollNo(int rollNo) {
        log.info("here is getStudentById");
        Criteria criteria = this.sessionFactory.openSession().createCriteria(Student.class);

        try {
            criteria.add(Restrictions.eq("rollNO", rollNo));
            Student student = (Student) criteria.uniqueResult();
            log.info(student.getRollNO() + "  " + student.getName() + " " + student.getAge());
            return student;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return null;
        }
    }

    /**
     *
     * @param student add new student in the database
     */
    @Override
    public void saveStudent(Student student) {
        Session session=this.sessionFactory.openSession();
       Transaction transactional=session.getTransaction();
        try{
            transactional.begin();
            session.saveOrUpdate(student);
            transactional.commit();
        }catch (Exception e){
            log.error(e.getMessage(),e);
           transactional.rollback();
        }
        finally {
            session.close();
        }
    }

    @Override
    public void updateStudent(Student student) {
        Session session=this.sessionFactory.openSession();
        Transaction transaction=session.getTransaction();
        try {
            transaction.begin();
            session.update(student);
            transaction.commit();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            transaction.rollback();
        }finally {
            session.close();
        }

    }

    @Override
    public void deleteStudentById(Student student) {
        Session session = this.sessionFactory.openSession();
        Transaction transaction = session.getTransaction();
        try {
            transaction.begin();
            session.delete(student);
            transaction.commit();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            transaction.rollback();
        } finally {
            session.close();
        }


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

}
