package com.hibernate.demo;
import com.hibernate.demo.entity.Course;
import com.hibernate.demo.entity.Instructor;
import com.hibernate.demo.entity.InstructorDetail;
import com.hibernate.demo.entity.Review;
import com.hibernate.demo.entity.Student;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class GetCoursesForStudent {

	public static void main(String[] args) {

		// create session factory
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class)
				.addAnnotatedClass(Course.class)
				.addAnnotatedClass(Review.class)
				.addAnnotatedClass(Student.class)
				.buildSessionFactory();
		
		// create session
		Session session = factory.getCurrentSession();
		
		try {
			Student st = new Student("moussa", "sow", "many 2 many");
			Course cr1 = new Course("poudre de ppp3");
			Course cr2 = new Course("chez l'agro3");
			
			session = factory.getCurrentSession();
			session.beginTransaction();

			session.save(st);
			cr1.addStudent(st);
			cr2.addStudent(st);
			session.save(cr1);
			session.save(cr2);
			
			session.getTransaction().commit();	

			session = factory.getCurrentSession();
			session.beginTransaction();
			Student theStudent = session.get(Student.class, st.getId());
			System.out.println("++++++++ " + theStudent.getCourses());
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