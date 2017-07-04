package me.ifma.activitybar.util;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.commons.dbutils.QueryRunner;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class MyUtils {
	public static final QueryRunner qr = new QueryRunner();
	/**
	 * 获取父类的泛型类型
	 * 
	 * @param c
	 * @return
	 */
	public static Connection getConnection(){
		String driver = "com.mysql.jdbc.Driver";
        String url="jdbc:mysql://localhost:3306/activitybar?useSSL=false&amp;useUnicode=true&amp;characterEncoding=utf-8";
        String user = "root"; 
        String password = "mazhiyuan";
         try {
			Class.forName(driver);
			Connection conn = DriverManager.getConnection(url, user, password);
			return conn;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public static Class getGenericClass(Class c) {
		ParameterizedType pt = (ParameterizedType) c.getGenericSuperclass();
		Type t = (Type) pt.getActualTypeArguments()[0];
		Class clazz = (Class) t;
		return clazz;
	}

	public static String sha1(String pwd) {
		try {
			MessageDigest sha1 = MessageDigest.getInstance("sha1");
			byte[] digest = sha1.digest(pwd.getBytes());
			String str = byteToHexStr(digest);
			return str;
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static String md5(String pwd) {
		try {
			MessageDigest md5 = MessageDigest.getInstance("md5");
			byte[] digest = md5.digest(pwd.getBytes());
			String str = byteToHexStr(digest);
			return str;
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String byteToHexStr(byte[] digest) {
		char[] c = {'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};
		StringBuffer sb = new StringBuffer();
		for(byte b:digest){
			sb.append(c[(b>>4)&15]);
			sb.append(c[b&15]);
		}
		return sb.toString();
	}

	public static int getRandom(){
		return(int)(Math.random()*1000000);
	}
}
