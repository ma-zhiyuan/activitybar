package me.ifma.activitybar.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import me.ifma.activitybar.dao.AdminDao;
import me.ifma.activitybar.entity.Admin;

@Service
public class AdminService {
	@Autowired
	AdminDao adminDao;
	
	public Admin getByNamePwd(String name,String pwd){
		Admin admin = adminDao.getByNamePwd(name, pwd);
		return admin;
	}
}
