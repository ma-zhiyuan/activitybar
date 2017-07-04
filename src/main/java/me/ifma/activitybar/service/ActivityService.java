package me.ifma.activitybar.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import me.ifma.activitybar.bean.Cretiera;
import me.ifma.activitybar.bean.Page;
import me.ifma.activitybar.dao.ActivityDao;
import me.ifma.activitybar.dao.ItemDao;
import me.ifma.activitybar.dao.UserDao;
import me.ifma.activitybar.entity.Activity;
import me.ifma.activitybar.entity.Item;
import me.ifma.activitybar.entity.User;
import me.ifma.activitybar.util.MyUtils;

@Service
public class ActivityService {
	@Autowired
	ActivityDao activityDao;
	@Autowired
	UserDao userDao;
	@Autowired
	ItemDao itemDao;
	
	public Page<Activity> getAll(Cretiera c) {
		Page<Activity> activitys = null;
		activitys = activityDao.getPage(c);
		return activitys;
	}

	public Activity getById(int id) {
		Activity activity = activityDao.getById(id);
		return activity;
	}
	
	public List<Activity> getAdminAll(){
		List<Activity> activitys = activityDao.getAdminAll();
		return activitys;
	}

	public void delete(int id) {
		activityDao.delete(id);
	}

	public void add(Activity activity) {
		activityDao.add(activity);
	}

	public void update(Activity activity) {
		activityDao.update(activity);
	}
	public Page<Activity> getWeiXinBook(String key) {
		Cretiera bc = new Cretiera();
		bc.setPageSize(4);
		bc.setMinPrice(0);
		bc.setMaxPrice(9999);
		bc.setPageNo(1);
		bc.setOrderBy(4);
		bc.setKeyword(key);
		Page<Activity> activitys = null;
		activitys = activityDao.getPage(bc);
		return activitys;
	}

	public void adds(List l) {
		activityDao.adds(l);
		
	}

	public List getUsersByActivityId(int id) {
		List<Item> items = itemDao.getByActivityId(id);
		List<User> users = new ArrayList<User>();
		for (Item item : items) {
			users.add(userDao.getById(item.getUserid()));
		}
		return users;
	}
}
