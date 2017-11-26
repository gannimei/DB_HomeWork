package com.netease.MyBatis_HomeWork;

import java.io.InputStream;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MyBatisTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String resource = "conf.xml";
		InputStream is = MyBatisTest.class.getClassLoader().getResourceAsStream(resource);
		SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(is);
		SqlSession sqlSession = sessionFactory.openSession();
		try {
			Op op = sqlSession.getMapper(Op.class);
			User user = op.getUser(1);
			System.out.println(user.getId() + " " + user.getUserName() + " ");
			for(Integer productId : user.getProducts()) {
				Product product = op.getProduct(productId);
				System.out.println(product.getProductName());
			}
		}finally {
			sqlSession.close();
		}
	}

}
