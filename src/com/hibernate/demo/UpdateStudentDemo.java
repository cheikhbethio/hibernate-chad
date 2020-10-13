package com.hibernate.demo;
import com.hibernate.demo.entity.Student;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class UpdateStudentDemo {

	public static void main(String[] args) {

		// create session factory
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
								.addAnnotatedClass(Student.class)
								.buildSessionFactory();
		
		// create session
		Session session = factory.getCurrentSession();
		
		try {			
			// create a student object
			System.out.println("Creating new student object...");
			Student createdStudent1 = new Student("Cheikh", "fall", "fall@luv2code.com");
			Student createdStudent2 = new Student("Serigne", "thioune", "paul@luv2code.com");
			session.beginTransaction();
			session.save(createdStudent1);
			session.save(createdStudent2);
			session.getTransaction().commit();
			System.out.println("Creation done!");
			
			System.out.println("Updating student1 first way........");
			session = factory.getCurrentSession();
			session.beginTransaction();
			Student gettedStudent= session.get(Student.class, createdStudent1.getId());
			gettedStudent.setFirstName("updated first time");
			session.getTransaction().commit();
			System.out.println("readed student" + gettedStudent);
			

			System.out.println("Updating all students second way........");
			session = factory.getCurrentSession();
			session.beginTransaction();
			session.createQuery("update Student set email='updatedEmail@emai.fr'")
			.executeUpdate();
			session.getTransaction().commit();
		}
		finally {
			factory.close();
		}
	}

}





