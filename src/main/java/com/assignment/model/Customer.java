package com.assignment.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Customer {
	private int customerId;
	private int name;
	private String address;
	private long phoneNo;
	private int balance;
	
	public void addMoney(int amount) {
		this.balance+=amount;
	}
	
	public void register() {
		// registration logic
	}
}
