package me.ifma.activitybar.bean;

import java.util.ArrayList;
import java.util.List;

public class Page<T> {
	private List<T> items = new ArrayList<T>();
	private Cretiera cretiera;

	public List<T> getItems() {
		return items;
	}

	public void setItems(List<T> items) {
		this.items = items;
	}

	public Object getCretiera() {
		return cretiera;
	}

	public void setCretiera(Cretiera cretiera) {
		this.cretiera = cretiera;
	}

	public Page(List<T> items, Cretiera bc) {
		this.items = items;
		this.cretiera = bc;
	}

}
