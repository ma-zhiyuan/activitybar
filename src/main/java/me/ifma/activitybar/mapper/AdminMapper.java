package me.ifma.activitybar.mapper;

import org.apache.ibatis.annotations.Param;

import me.ifma.activitybar.entity.Admin;

public interface AdminMapper {
	public Admin getByNamePwd(@Param("name")String name,@Param("pwd")String pwd);
}
