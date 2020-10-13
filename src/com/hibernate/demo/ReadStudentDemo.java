package com.hibernate.demo;
import com.hibernate.demo.entity.Student;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class ReadStudentDemo {

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
			Student tempStudent = new Student("Paul", "Wall", "paul@luv2code.com");
			session.beginTransaction();
			session.save(tempStudent);
			session.getTransaction().commit();
			System.out.println("Creation done!");
			
			System.out.println("Read student");
			session = factory.getCurrentSession();
			session.beginTransaction();
			Student gettedStudent= session.get(Student.class, tempStudent.getId());
			session.getTransaction().commit();
			System.out.println("readed student" + gettedStudent);
		}
		finally {
			factory.close();
		}
	}

}





