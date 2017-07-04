package me.ifma.activitybar.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import me.ifma.activitybar.entity.User;
import me.ifma.activitybar.entity.weixinentity;
import me.ifma.activitybar.mapper.UserMapper;
@Repository
public class UserDao {
	@Autowired
	UserMapper userMapper;
	
	public User getById(int id){
		return userMapper.getById(id);
	}
	
	public User get(String name, String password) {
		User u = userMapper.getByNamePwd(name,password);
		return u;
	}
	public String getName(String name) {
		String n = userMapper.getName(name);
		return n;
	}
	public void add(User u) {
		userMapper.add(u);
	}
	public void update(User u) {
		userMapper.update(u);
	}
	public User getByOpenId(String openId) {
		System.out.println(userMapper);
		User user = userMapper.getById(userMapper.getByOpenId(openId));
		return user;
	}
	public Integer getBindId(int userId) {
		Integer id = userMapper.getBindId(userId);
		return id;
	}
	public int bind(int id, String openid) {
		userMapper.bind(id,openid);
		return 1;
	}
	public String getOpenId(String openid) {
		String id = userMapper.getOpenId(openid);
		return id;
	}

	public int getIdByName(String name) {
		return userMapper.getIdByName(name);
	}

	
}
