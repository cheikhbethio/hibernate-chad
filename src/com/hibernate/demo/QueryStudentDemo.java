package com.hibernate.demo;
import com.hibernate.demo.entity.Student;

import java.util.Iterator;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class QueryStudentDemo {

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
			System.out.println("with query ");
			session.beginTransaction();
			List<Student> theStudents = session.createQuery("from Student").getResultList();
			displayStudent(theStudents);
			System.out.println("with query");

			List<Student> studentsQuery = session.createQuery("from Student s where s.id='2'").getResultList();
			displayStudent(studentsQuery);
			session.getTransaction().commit();
			System.out.println("Creation done!");
		}
		finally {
			factory.close();
		}
	}

	private static void displayStudent(List<Student> theStudents) {
		for (Student student : theStudents) {
			System.out.println("+++ "+ student);
		}
	}

}





