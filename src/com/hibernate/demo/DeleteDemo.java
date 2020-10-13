package com.hibernate.demo;
import com.hibernate.demo.entity.Instructor;
import com.hibernate.demo.entity.InstructorDetail;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DeleteDemo {

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
			// purge db
			session = factory.getCurrentSession();
			session.beginTransaction();
			session.createQuery("delete from Instructor").executeUpdate();
			session.createQuery("delete from InstructorDetail").executeUpdate();
			session.getTransaction().commit();
			
			// create an instructor object
			InstructorDetail instDetail = new InstructorDetail("youtubAdmoin", "foot and philo");
			Instructor inst = new Instructor("inst", "to delete", "toDelete@jj.fr");		
			inst.setInstructorDetail(instDetail);
			session = factory.getCurrentSession();
			session.beginTransaction();
			session.save(inst);
			session.getTransaction().commit();
			System.out.println("Creation Done!");
			
			// getting object to delete
			session = factory.getCurrentSession();
			session.beginTransaction();
			Instructor instToDelete = session.get(Instructor.class, inst.getId());
			session.getTransaction().commit();
			

			// deleting object to delete
			session = factory.getCurrentSession();
			session.beginTransaction();
			session.delete(instToDelete);
			session.getTransaction().commit();

		}
		finally {
			factory.close();
		}
	}

}





