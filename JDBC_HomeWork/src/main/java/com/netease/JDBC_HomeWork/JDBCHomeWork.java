package com.netease.JDBC_HomeWork;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCHomeWork {

	static final String DRIVER_NAME = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost:3306/test?useUnicode=true&characterEncoding=UTF8&useSSL=true";
	static final String DB_USER_NAME = "root";
	static final String DB_PASSWORD = "123456";
	
	public static void doJDBCTest() {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			Class.forName(DRIVER_NAME);
			conn = DriverManager.getConnection(DB_URL, DB_USER_NAME, DB_PASSWORD);
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select * from Product where Id=1");
			while (rs.next()) {
				String ProductName = rs.getString("ProductName");
				int Inventory = rs.getInt("Inventory");
				System.out.println("ProductName: " + ProductName);
				System.out.println("Inventory: " + Inventory);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (conn != null) {
					conn.close();
				}
				if (stmt != null) {
					stmt.close();
				}
				if (rs != null) {
					rs.close();
				}
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		doJDBCTest();
	}

}
