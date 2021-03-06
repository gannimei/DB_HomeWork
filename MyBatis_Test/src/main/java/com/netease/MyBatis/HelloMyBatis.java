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
		SqlSession sqlSession = sessionFactory.openSession();
		try {
			GetUserInfo getUserInfo = sqlSession.getMapper(GetUserInfo.class);
			User user = getUserInfo.GetUser(1);
			System.out.println(user.getId() + " " + user.getUserName() + " ");
			System.out.println(user.getCourses().get(0).getCourseName() + " ");
			System.out.println(user.getCourses().get(0).getTeacher().getTeacherName());
		} finally {
			sqlSession.close();
		}
	}

}
