package com.hibernate.demo;
import com.hibernate.demo.entity.Course;
import com.hibernate.demo.entity.Instructor;
import com.hibernate.demo.entity.InstructorDetail;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class GetInstrucAndCourses {

	public static void main(String[] args) {

		// create session factory
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class)
				.addAnnotatedClass(Course.class)
				.buildSessionFactory();
		
		// create session
		Session session = factory.getCurrentSession();
		
		try {
			
			// create an instructor object
			InstructorDetail instDetail = new InstructorDetail("onToMany", "foot and philo");
			Instructor inst = new Instructor("mooussa", "one to many bi direction", "toDelete@purge.fr");		
			inst.setInstructorDetail(instDetail);
			session = factory.getCurrentSession();
			session.beginTransaction();
			session.createQuery("delete from Instructor").executeUpdate();
			session.save(inst);
			session.getTransaction().commit();
			System.out.println("Creation Done!");
			
			// create courses
			session = factory.getCurrentSession();			
			session.beginTransaction();
			Instructor theInstructor = session.get(Instructor.class, inst.getId());
			session.createQuery("delete from Course").executeUpdate();
			Course course1 = new Course("hibernate course1");
			Course course2 = new Course("hibernate course2");
			Course course3 = new Course("hibernate course3");
			
			//add Courses
			theInstructor.add(course1);
			theInstructor.add(course2);
			theInstructor.add(course3);
			
			// save courses
			session.save(course1);
			session.save(course2);
			session.save(course3);
			
			session.getTransaction().commit();
			
			
			// get course by instructor
			session = factory.getCurrentSession();			
			session.beginTransaction();
			Instructor theInst = session.get(Instructor.class, inst.getId());
			System.out.println("+++++ the instructor " + theInst);
			System.out.println("+++++ the course for the instructor " + theInst.getCourses());
			session.getTransaction().commit();
			
			// get instructor bay courses
			session = factory.getCurrentSession();			
			session.beginTransaction();
			Course theCourse = session.get(Course.class, course1.getId());
			session.delete(theCourse);
			session.getTransaction().commit();			
			

		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			session.close();
			factory.close();
		}
	}

}





