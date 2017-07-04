package me.ifma.activitybar.entity;

public class weixinentity {
	private int user_id;
	private String openid;
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public weixinentity(int user_id, String openid) {
		super();
		this.user_id = user_id;
		this.openid = openid;
	}
	public String getOpenid() {
		return openid;
	}
	public void setOpenid(String openid) {
		this.openid = openid;
	}
	public weixinentity() {
		super();
	}
	@Override
	public String toString() {
		return "weixinentity [user_id=" + user_id + ", openid=" + openid + "]";
	}
	
}
