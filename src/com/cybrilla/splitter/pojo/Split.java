package com.cybrilla.splitter.pojo;

public abstract class Split {

	private User user;
	private double amount;
	public Split() {
		// TODO Auto-generated constructor stub
	}
	public Split(User user) {
		super();
		this.user = user;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	@Override
	public String toString() {
		return "Split [user=" + user + ", amount=" + amount + "]";
	}
}
