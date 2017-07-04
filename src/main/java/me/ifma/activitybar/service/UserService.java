package me.ifma.activitybar.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import me.ifma.activitybar.dao.UserDao;
import me.ifma.activitybar.entity.User;
@Service
public class UserService {
	@Autowired
	UserDao userDao;
	public User get(String name, String password) {
		User u = userDao.get(name,password);
		if(u!=null){
			
		}
		return u;
	}
	public boolean verifyName(String name) {
		String n = userDao.getName(name);
		if ("".equals(n)||n==null) {
			return false;
		} else {
			return true;
		}
	}
	public User regist(String name, String pwd) {
		User u = new User(null,name,pwd,0D,1,20,null);
		userDao.add(u);
		User user = userDao.get(name, pwd);
		return user;
	}
	public User registByEmail(String email, String pwd) {
		User u = new User(null,email,pwd,0D,1,20,email);
		userDao.add(u);
		User user = userDao.get(email, pwd);
		return user;
	}
	public void update(User u) {
		userDao.update(u);
	}
	
	public int toBind(String name, String pwd, String openid) {
		//成功0，用户名不存在1，密码不正确2.账号已绑定过了3。
		boolean b = this.verifyName(name);
		//用户名不存在
		if(!b){
			return 1;
		}
		User u = this.get(name, pwd);
		//密码不正确
		if(u==null){
			return 2;
		}
		//查看当前的微信号是否绑定其它账号
		boolean b2 = this.verifyOpenId(openid);
		if(b2){
			return 3;
		}
		//输入的用户有是否被其它微信号绑定
		boolean b3 = this.isBind(u.getId());
		if(b3){
			return 4;
		}
		int i = this.bind(u.getId(),openid);
		if(i==1){
			return 0;
		}
		return 99;
	}
	
	public boolean isBind(int userId) {
		Integer id = userDao.getBindId(userId);
		if(id==null){
			return false;
		}else{
			return true;
		}
	}
	
	public int bind(int id, String openid) {
		return userDao.bind(id,openid);
	}

	/**
	 * 验证是否存在这样一个openid
	 * @param openid
	 * @return
	 */
	public boolean verifyOpenId(String openid) {
		String id = userDao.getOpenId(openid);
		if(id==null){
			return false;
		}else{
			return true;
		}
	}

	public User getById(int id){
		return userDao.getById(id);
	}
	
	public User getByOpenId(String openId) {
		System.out.println(userDao);
		System.out.println(openId);
		User u =  userDao.getByOpenId(openId);
		return u;
	}
	public int getIdByName(String name) {
		return userDao.getIdByName(name);
	}

}
