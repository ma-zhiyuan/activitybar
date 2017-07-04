package me.ifma.activitybar.entity;

import java.util.Date;

public class Activity {
	private Integer id;
	private String name;
	private Date date;
	private String surface;
	private String place;
	private Integer personnum;
	private Double cost;
	private String contact;
	private String detail;
	private int creator;
	

	public int getCreator() {
		return creator;
	}

	public void setCreator(int creator) {
		this.creator = creator;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getSurface() {
		return surface;
	}

	public void setSurface(String surface) {
		this.surface = surface;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public Integer getPersonnum() {
		return personnum;
	}

	public void setPersonnum(Integer personnum) {
		this.personnum = personnum;
	}

	public Double getCost() {
		return cost;
	}

	public void setCost(Double cost) {
		this.cost = cost;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public Activity() {
		super();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((contact == null) ? 0 : contact.hashCode());
		result = prime * result + ((cost == null) ? 0 : cost.hashCode());
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + ((detail == null) ? 0 : detail.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((personnum == null) ? 0 : personnum.hashCode());
		result = prime * result + ((place == null) ? 0 : place.hashCode());
		result = prime * result + ((surface == null) ? 0 : surface.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Activity other = (Activity) obj;
		if (contact == null) {
			if (other.contact != null)
				return false;
		} else if (!contact.equals(other.contact))
			return false;
		if (cost == null) {
			if (other.cost != null)
				return false;
		} else if (!cost.equals(other.cost))
			return false;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (detail == null) {
			if (other.detail != null)
				return false;
		} else if (!detail.equals(other.detail))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (personnum == null) {
			if (other.personnum != null)
				return false;
		} else if (!personnum.equals(other.personnum))
			return false;
		if (place == null) {
			if (other.place != null)
				return false;
		} else if (!place.equals(other.place))
			return false;
		if (surface == null) {
			if (other.surface != null)
				return false;
		} else if (!surface.equals(other.surface))
			return false;
		return true;
	}

	public Activity(Integer id, String name, Date date, String surface, String place, Integer personnum, Double cost,
			String contact, String detail) {
		super();
		this.id = id;
		this.name = name;
		this.date = date;
		this.surface = surface;
		this.place = place;
		this.personnum = personnum;
		this.cost = cost;
		this.contact = contact;
		this.detail = detail;
	}

	public Activity(Integer id, String name, Date date, String surface, String place, Integer personnum, Double cost,
			String contact, String detail, int creator) {
		super();
		this.id = id;
		this.name = name;
		this.date = date;
		this.surface = surface;
		this.place = place;
		this.personnum = personnum;
		this.cost = cost;
		this.contact = contact;
		this.detail = detail;
		this.creator = creator;
	}

	public Activity(String name) {
		super();
		this.name = name;
	}

	@Override
	public String toString() {
		return "Activity [id=" + id + ", name=" + name + ", date=" + date + ", surface=" + surface + ", place=" + place
				+ ", personnum=" + personnum + ", cost=" + cost + ", contact=" + contact + ", detail=" + detail + "]";
	}

}
