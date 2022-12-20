package com.app.core;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Customer implements Comparable<Customer> {
//id(int) name(string),email(string),password(string),registrationAmount(double),dob(Date),type (ServicePlan : enum)
	private int customeId;
	private String customerName;
	private String email;
	private String password;
	private double registrationAmount;
	private Date dob;
	private ServicePlan plan;
	private static int idGenerator;
	public static SimpleDateFormat sdf;
	static {
		idGenerator = 1;
		sdf = new SimpleDateFormat("dd/MM/yyyy");
	}

	public Customer(String customerName, String email, String password, double registrationAmount, Date dob,
			ServicePlan plan) {
		super();
		this.customerName = customerName;
		this.email = email;
		this.password = password;
		this.registrationAmount = registrationAmount;
		this.dob = dob;
		this.plan = plan;
		this.customeId = idGenerator++;
	}

	public String getEmail() {
		return email;
	}

	public String getPassword() {
		return password;
	}

	public Customer(String email, String password) {
		super();
		this.email = email;
		this.password = password;
	}

	public Customer(String email) {
		super();
		this.email = email;
	}
	
	public ServicePlan getPlan() {
		return plan;
	}

	public Date getDob() {
		return dob;
	}

	public int getCustomeId() {
		return customeId;
	}

	@Override
	public String toString() {
		return "Customer [customeId=" + customeId + ", customerName=" + customerName + ", email=" + email
				+ ", registrationAmount=" + registrationAmount + ", dob=" + sdf.format(dob) + ", plan=" + plan + "]";
	}

	@Override
	public boolean equals(Object o) {
		if (o instanceof Customer)
			return this.email.equals(((Customer) o).email);
		return false;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public int compareTo(Customer c) {
		return this.email.compareTo(c.email);
	}

}
