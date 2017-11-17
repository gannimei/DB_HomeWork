package com.netease.MyBatis;

import java.io.InputStream;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class HelloMyBatis {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String resource = "conf.xml";
		InputStream is = HelloMyBatis.class.getClassLoader().getResourceAsStream(resource);
		SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(is);
		SqlSession sqlSession = sessionFactory.openSession(true);
		try {
			GetUserInfo getUserInfo = sqlSession.getMapper(GetUserInfo.class);
			getUserInfo.deleteUser(2);
		} finally {
			sqlSession.close();
		}
	}

}
