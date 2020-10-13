package com.hibernate.demo;
import com.hibernate.demo.entity.Course;
import com.hibernate.demo.entity.Instructor;
import com.hibernate.demo.entity.InstructorDetail;
import com.hibernate.demo.entity.Review;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DeleteCourseAndReviews {

	public static void main(String[] args) {

		// create session factory
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class)
				.addAnnotatedClass(Course.class)
				.addAnnotatedClass(Review.class)
				.buildSessionFactory();
		
		// create session
		Session session = factory.getCurrentSession();
		
		try {
			
			Course course = new Course("learn hibernate ");
			
			Review r1 = new Review("by moussa");
			Review r2 = new Review("by mbs");
			Review r3 = new Review("by fatou");
			
			course.addReview(r1);
			course.addReview(r2);
			course.addReview(r3);
			
			session = factory.getCurrentSession();
			session.beginTransaction();
			session.createQuery("delete from Review").executeUpdate();
			session.createQuery("delete from Course").executeUpdate();
			session.save(course);
			
			session.getTransaction().commit();
			System.out.println("Creation Done!");
				

			session = factory.getCurrentSession();
			session.beginTransaction();
			Course theCourse = session.get(Course.class, course.getId());
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









