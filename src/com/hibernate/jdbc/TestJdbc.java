package com.hibernate.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;

public class TestJdbc {

	public static void main(String[] args) {
		String jdbcUrl = "jdbc:mysql://localhost:9998/hb-01-one-to-one-uni?useSSL=false";
		String user= "hbstudent";
		String pass= "hbstudent";
		try {
			System.out.println("1111111111111");
			Connection myConn = DriverManager.getConnection(jdbcUrl, user, pass);
			System.out.println("success");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
