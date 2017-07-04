package me.ifma.activitybar.test;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

import javax.ws.rs.ApplicationPath;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import me.ifma.activitybar.bean.Cretiera;
import me.ifma.activitybar.entity.Activity;
import me.ifma.activitybar.entity.User;
import me.ifma.activitybar.mapper.ActivityMapper;
import me.ifma.activitybar.mapper.UserMapper;

public class ActivityTest {
	@Test
	public void getAllTest() {
		System.out.println("-----");
		ApplicationContext ac = new ClassPathXmlApplicationContext("spring/applicationContext.xml");
		System.out.println("----++++++++-" + ac);
		ActivityMapper am = (ActivityMapper) ac.getBean("activityMapper");
		System.out.println("-----" + am);
		// long count = am.getCount(0,99,"");
		// long count = am.getCount(new Cretiera("",0,999,1,9,1));
		// System.out.println(count);
		List<Activity> all = am.getAll(new Cretiera("", 0, 999, 1, 9, 1), "id asc", 1, 4);
		System.out.println("------------------" + all);
	}

	@Test
	public void getuserTest() {
		System.out.println("-----");
		ApplicationContext ac = new ClassPathXmlApplicationContext("spring/applicationContext.xml");
		System.out.println("----++++++++-" + ac);
		UserMapper um = (UserMapper) ac.getBean("userMapper");
		System.out.println("-----" + um);
		User byNamePwd = um.getByNamePwd("ma", "BBB8AAE57C104CDA40C93843AD5E6DB8");
		System.out.println(byNamePwd);
	}

	@Test
	public void getName() {
		ApplicationContext ac = new ClassPathXmlApplicationContext("spring/applicationContext.xml");
		UserMapper um = (UserMapper) ac.getBean("userMapper");
		String byNamePwd = um.getName("ma");
		System.out.println(byNamePwd);
	}

	@Test
	public void testitem(){
		String s ="hello";
		String t = "hello";
		char[] a = {'h','e','l','l','o'};
		System.out.println(s.equals(t));
		System.out.println(t.equals(a));
		System.out.println(s==t);
		System.out.println(t.equals(new String("hello")));
	}
	
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		Double d = s.nextDouble();
		System.out.println(d);
	}

}
