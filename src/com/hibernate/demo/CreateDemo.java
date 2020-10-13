package com.hibernate.demo;
import com.hibernate.demo.entity.Instructor;
import com.hibernate.demo.entity.InstructorDetail;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CreateDemo {

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
			Instructor inst = new Instructor("moussa", "sow", "m.sow@jj.fr");		
			inst.setInstructorDetail(instDetail);
			
			// start a transaction
			session.beginTransaction();
			
			// saving inst and inst detail in 2 diff tables
			session.save(inst);
			
			// commit transaction
			session.getTransaction().commit();
			
			System.out.println("Done!");
		}
		finally {
			factory.close();
		}
	}

}





