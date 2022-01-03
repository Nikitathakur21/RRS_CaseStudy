package com.railwayBooking.PaymentServices.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection= "PaymentService")
public class Payment 
{
	@Id
	private String name;
	private long contactNo;
	private int age;
	private String email;
	
	public Payment(String name, long contactNo, int age, String email) {
		super();
		this.name = name;
		this.contactNo = contactNo;
		this.age = age;
		this.email = email;
	}

	public Payment() {
		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getContactNo() {
		return contactNo;
	}

	public void setContactNo(long contactNo) {
		this.contactNo = contactNo;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "Payment [name=" + name + ", contactNo=" + contactNo + ", age=" + age + ", email=" + email + "]";
	}
	
	
	

}