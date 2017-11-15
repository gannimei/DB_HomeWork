package com.netease.Transaction_HomeWork;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.apache.commons.dbcp2.BasicDataSource;;

public class TransactionHomeWork {

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

	static void Buy(String userName, String ProductName, int Number) {
		Connection conn = null;
		PreparedStatement descInventory = null;
		PreparedStatement insertOrder = null;
		try {
			conn = ds.getConnection();
			conn.setAutoCommit(false);
			descInventory = conn.prepareStatement(
					"update Inventory set Inventory=Inventory-? where ProductName=? and Inventory>=?");
			descInventory.setInt(1, Number);
			descInventory.setString(2, ProductName);
			descInventory.setInt(3, Number);
			insertOrder = conn.prepareStatement("insert into `Order`(buyer, ProductName) value(?,?)");
			insertOrder.setString(1, userName);
			insertOrder.setString(2, ProductName);
			int result = descInventory.executeUpdate();
			if (result > 0) {
				result = insertOrder.executeUpdate();
				if (result > 0) {
					conn.commit();
					System.out.println("下单成功");
				}
				else {
					conn.rollback();
					System.out.println("下单失败");
				}
			} else {
				conn.rollback();
				System.out.println("库存不足");
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			if (conn != null) {
				try {
					conn.rollback();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		} finally {
			try {
				if (conn != null) {
					conn.close();
				}
				if (descInventory != null) {
					descInventory.close();
				}
				if (insertOrder != null) {
					insertOrder.close();
				}
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		initPool();
		Buy("XiaoMing", "bag", 1);
	}

}
