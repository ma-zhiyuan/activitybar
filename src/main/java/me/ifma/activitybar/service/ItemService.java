package me.ifma.activitybar.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import me.ifma.activitybar.dao.ItemDao;
import me.ifma.activitybar.entity.Item;

@Service
public class ItemService {
	@Autowired
	ItemDao itemDao;

	public void add(int userid, int activityid) {
		itemDao.add(userid, activityid);
	}

	public Item get(int userid, int activityid) {
		Item item = itemDao.get(userid, activityid);
		return item;
	}

	public List<Item> getByUserid(Integer id) {
		List<Item> items = itemDao.getByUserid(id);
		return items;
	}

	public Integer getCount(int id) {
		return itemDao.getCount(id);
	}

	public void delete(int id) {
		itemDao.delete(id);
	}
}
