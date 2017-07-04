package me.ifma.activitybar.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import me.ifma.activitybar.entity.Admin;
import me.ifma.activitybar.mapper.AdminMapper;

@Repository
public class AdminDao {
	@Autowired
	AdminMapper adminMapper;
	
	public Admin getByNamePwd(String name,String pwd){
		Admin admin = adminMapper.getByNamePwd(name, pwd);
		return admin;
	}
}
