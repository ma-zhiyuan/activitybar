package me.ifma.activitybar.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import me.ifma.activitybar.entity.Item;

public interface ItemMapper {
	void add(@Param("userid")int userid, @Param("activityid")int activityid);

	Item get(@Param("userid")int userid, @Param("activityid")int activityid);

	List<Item> getByUserid(Integer userid);

	Integer getCount(int id);

	List<Item> getByActivityId(int id);

	void delete(int id);
}