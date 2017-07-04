package me.ifma.activitybar.entity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class User {
	private Integer id;
	private String name;
	private String password;
	private Double balance;
	private Integer gender;
	private Integer age;
	private String email;
	private List<Activity> items = new ArrayList<Activity>();

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", password=" + password + ", balance=" + balance + ", gender="
				+ gender + ", age=" + age + ", email=" + email + "]";
	}

	public User(Integer id, String name, String password, Double balance, Integer gender, Integer age, String email) {
		super();
		this.id = id;
		this.name = name;
		this.password = password;
		this.balance = balance;
		this.gender = gender;
		this.age = age;
		this.email = email;
	}

	public User() {
		super();
	}

	public List<Activity> getItems() {
		return items;
	}

	public void setItems(List<Activity> items) {
		this.items = items;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Double getBalance() {
		return balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}

	public Integer getGender() {
		return gender;
	}

	public void setGender(Integer gender) {
		this.gender = gender;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
