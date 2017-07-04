package me.ifma.activitybar.mapper;

import org.apache.ibatis.annotations.Param;

import me.ifma.activitybar.entity.User;
import me.ifma.activitybar.entity.weixinentity;

public interface UserMapper {
	User getById(int id);
	
	User getByNamePwd(@Param("name")String name,@Param("pwd")String pwd);

	String getName(String name);

	void add(User u);

	void update(User u);

	Integer getByOpenId(String openId);

	Integer getBindId(int userId);

	void bind(@Param("id")int id, @Param("openid")String openid);

	String getOpenId(String openid);

	Integer getIdByName(String name);
}
