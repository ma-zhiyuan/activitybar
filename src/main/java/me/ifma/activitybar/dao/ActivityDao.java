package me.ifma.activitybar.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import me.ifma.activitybar.bean.Cretiera;
import me.ifma.activitybar.bean.Page;
import me.ifma.activitybar.entity.Activity;
import me.ifma.activitybar.mapper.ActivityMapper;
import me.ifma.activitybar.util.MyUtils;

@Repository
public class ActivityDao {
	@Autowired
	ActivityMapper activityMapper;

	public Page<Activity> getPage(Cretiera c) {
		Page<Activity> page = null;
		Long count = activityMapper.getCount(c);
		c.setCount(Integer.valueOf(count.toString()));
		int orderbyInt = c.getOrderBy();
		String orderby = "date asc";
		switch (orderbyInt) {
		case 1:
			orderby = "cost asc";
			break;
		case 2:
			orderby = "cost desc";
			break;
		case 3:
			orderby = "date asc";
			break;
		case 4:
			orderby = "date desc";
			break;
		default:
			orderby = "date asc";
		}
		int from = (c.getPageNo()-1)*c.getPageSize();
		int end = c.getPageNo()*c.getPageSize();
		List<Activity> activitys = activityMapper.getAll(c,orderby,from,end);
		Page<Activity> activitypage = new Page<Activity>(activitys,c);
		return activitypage;
	}

	public Activity getById(int id) {
		Activity activity = activityMapper.getById(id);
		return activity;
	}

	public List<Activity> getAdminAll() {
		List<Activity> activitys = activityMapper.getAdminAll();
		return activitys;
	}

	public void delete(int id) {
		activityMapper.delete(id);
	}

	public void add(Activity activity) {
		activityMapper.add(activity);
	}

	public void update(Activity activity) {
		activityMapper.update(activity);
	}

	public Page<Activity> getPageByWeixin(Cretiera bc) {
		Page<Activity> page = null;
		String sql ;
		String sql2;
		try {
			double min = bc.getMinPrice();
			double max = bc.getMaxPrice();
			String key = bc.getKeyword();
			int orderbyInt = bc.getOrderBy();
			String orderby = "id asc";
			int pageNo = bc.getPageNo();
			int pageSize = bc.getPageSize();
			switch(orderbyInt){
				case 1:
					orderby = "cost asc";
					break;
				case 2:
					orderby = "cost desc";
					break;
				case 3:
					orderby = "date asc";
					break;
				case 4:
					orderby = "date desc";
					break;
				default:
					orderby = "id desc";
			}
			//查询有多少条数据
			sql2="select count(id) from activity where cost between "+min+" and "+max+" and (name like '%"+key+"%' or detail like '%"+key+"%')";
			Object e = this.getE(sql2, min,max);
			int count = ((Long)e).intValue();
			bc.setCount(count);
			//计算每页起始和结束
			int from = (pageNo-1)*pageSize;
			int end = pageNo*pageSize;
			//查询语句
			sql = "SELECT * FROM (SELECT * FROM activity"
					+"WHERE cost" 
					+"BETWEEN "+min+" and "+max
					+"and (name like '%"+key+"%' or detail like '%"+key+"%')"
					+"order by "+orderby+" ) activity LIMIT "+from+","+end;
			//获取当前页的所有书的集合
			List<Activity> books = this.getAll(sql,min,max,from,end);
			//封装成一个Page对象
			Page<Activity> bookPage  = new Page<Activity>(books,bc);
			return bookPage;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return page;
	}
	public Object getE(String sql,Object ... args ) throws SQLException{
		Connection conn = MyUtils.getConnection();
		Object obj = null;
		Statement statement = conn.createStatement();
		ResultSet rs = statement.executeQuery(sql);
		while(rs.next()){
			obj = rs.getRowId(0);
		}
		System.out.println(obj);
		return obj;
	}
	public List<Activity> getAll(String sql,Object ... args) throws SQLException{
		Connection conn = MyUtils.getConnection();
		Statement statement = conn.createStatement();
		ResultSet rs = statement.executeQuery(sql);
		List<Activity> ts = new ArrayList<>();
		while(rs.next()){
			int id = rs.getInt("id");
			String name = rs.getString("name");
			Date date = rs.getDate("date");
			String surface = rs.getString("surface");
			String place=rs.getString("place");
			int personnum =rs.getInt("personnum");
			Double cost = rs.getDouble("cost");
			String contact=rs.getString("contact");
			String detail=rs.getString("detail");
			Activity a = new Activity(id, name, date, surface, place, personnum, cost, contact, detail);
			ts.add(a);
		}
		System.out.println(ts);
		return ts;
	}

	public void adds(List l) {
		activityMapper.adds(l);
	}

}
