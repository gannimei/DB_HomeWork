package com.netease.DBCP_HomeWork;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.commons.dbcp2.BasicDataSource;

public class DBCPHomeWork {

	static BasicDataSource ds = null;
	static final String DRIVER_NAME = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost:3306/test?useUnicode=true&characterEncoding=UTF8&useSSL=true";
	static final String DB_USER_NAME = "root";
	static final String DB_PASSWORD = "123456";

	public static void initPool() {
		ds = new BasicDataSource();
		ds.setUrl(DB_URL);
		ds.setDriverClassName(DRIVER_NAME);
		ds.setUsername(DB_USER_NAME);
		ds.setPassword(DB_PASSWORD);
	}

	public static void doJDBCTest() {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			conn = ds.getConnection();
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
		initPool();
		doJDBCTest();
	}

}
