package com.netease.SqlInject_HomeWork;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.sql.PreparedStatement;

public class SqlInjectHomeWork {

	static final String DRIVER_NAME = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost:3306/test?useUnicode=true&characterEncoding=UTF8&useSSL=true";
	static final String DB_USER_NAME = "root";
	static final String DB_PASSWORD = "123456";

	public static void getStudent(String name) throws ClassNotFoundException {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			Class.forName(DRIVER_NAME);
			conn = DriverManager.getConnection(DB_URL, DB_USER_NAME, DB_PASSWORD);
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select name,score from student where name='" + name + "'");
			while (rs.next()) {
				System.out.println(rs.getString("name") + ":" + rs.getInt("score"));
			}
		} catch (SQLException e) {
			// ignore
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (Exception e) {
					// ignore
				}
			}
			if (stmt != null) {
				try {
					stmt.close();
				} catch (Exception e) {
					// ignore
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					// ignore
				}
			}
		}
	}

	public static void getStudentSecurity(String name) throws ClassNotFoundException {
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		try {
			Class.forName(DRIVER_NAME);
			conn = DriverManager.getConnection(DB_URL, DB_USER_NAME, DB_PASSWORD);
			psmt = conn.prepareStatement("select name,score from student where name=?");
			psmt.setString(1, name);
			rs = psmt.executeQuery();
			while (rs.next()) {
				System.out.println(rs.getString("name") + ":" + rs.getInt("score"));
			}
		} catch (SQLException e) {
			// ignore
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (Exception e) {
					// ignore
				}
			}
			if (psmt != null) {
				try {
					psmt.close();
				} catch (Exception e) {
					// ignore
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					// ignore
				}
			}
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			getStudent("1' or '1'='1");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
