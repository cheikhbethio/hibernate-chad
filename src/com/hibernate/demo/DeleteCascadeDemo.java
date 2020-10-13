package com.hibernate.demo;
import com.hibernate.demo.entity.Instructor;
import com.hibernate.demo.entity.InstructorDetail;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DeleteCascadeDemo {

	public static void main(String[] args) {

		// create session factory
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class)
				.buildSessionFactory();
		
		// create session
		Session session = factory.getCurrentSession();
		
		try {
			
			// create an instructor object
			InstructorDetail instDetail = new InstructorDetail("youtubAdmoin", "foot and philo");
			Instructor inst = new Instructor("inst", "bi direction", "toDelete@purge.fr");		
			inst.setInstructorDetail(instDetail);
			session = factory.getCurrentSession();
			session.beginTransaction();
			session.createQuery("delete from Instructor").executeUpdate();
			session.createQuery("delete from InstructorDetail").executeUpdate();
			session.save(inst);
			session.getTransaction().commit();
			System.out.println("Creation Done!");
			
			// getting object to delete
			session = factory.getCurrentSession();
			session.beginTransaction();
			InstructorDetail tempDetail = session.get(InstructorDetail.class, instDetail.getId());
			System.out.println("++++++++ => " + tempDetail.getInstructor());
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





