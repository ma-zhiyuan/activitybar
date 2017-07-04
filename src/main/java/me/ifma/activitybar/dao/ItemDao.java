package me.ifma.activitybar.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import me.ifma.activitybar.entity.Item;
import me.ifma.activitybar.mapper.ItemMapper;

@Repository
public class ItemDao {
	@Autowired
	ItemMapper itemMapper;

	public void add(int userid, int activityid) {
		itemMapper.add(userid, activityid);
	}

	public Item get(int userid, int activityid) {
		Item item = itemMapper.get(userid, activityid);
		return item;
	}

	public List<Item> getByUserid(Integer id) {
		List<Item> items = itemMapper.getByUserid(id);
		return items;
	}

	public Integer getCount(int id) {
		return itemMapper.getCount(id);
	}

	public List<Item> getByActivityId(int id) {
		return itemMapper.getByActivityId(id);
	}

	public void delete(int id) {
		itemMapper.delete(id);
	}
}
