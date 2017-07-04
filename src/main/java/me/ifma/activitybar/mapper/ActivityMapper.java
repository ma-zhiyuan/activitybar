package me.ifma.activitybar.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import me.ifma.activitybar.bean.Cretiera;
import me.ifma.activitybar.entity.Activity;

public interface ActivityMapper {
	
	long getCount(Cretiera cretiera);

	List<Activity> getAll(@Param("c")Cretiera c,@Param("orderby")String orderby, @Param("from")int from, @Param("end")int end);

	Activity getById(int id);

	List<Activity> getAdminAll();

	void delete(int id);

	void add(Activity activity);

	void update(Activity activity);

	void adds(List l);
}
