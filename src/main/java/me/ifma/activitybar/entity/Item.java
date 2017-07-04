package me.ifma.activitybar.entity;

public class Item {
	private Integer userid;
	private Integer activityid;
	public Integer getUserid() {
		return userid;
	}
	public void setUserid(Integer userid) {
		this.userid = userid;
	}
	public Integer getActivityid() {
		return activityid;
	}
	public void setActivityid(Integer activityid) {
		this.activityid = activityid;
	}
	public Item() {
		super();
	}
	public Item(Integer userid, Integer activityid) {
		super();
		this.userid = userid;
		this.activityid = activityid;
	}
	@Override
	public String toString() {
		return "Item [userid=" + userid + ", activityid=" + activityid + "]";
	}
	
}
