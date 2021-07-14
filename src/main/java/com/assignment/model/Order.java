package com.assignment.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "orders")
public class Order {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int orderId;
	private int totalAmount;
	private int customerId;
//	private Date dateOfDelivery;
	private Date dateOfOrder;
	
	@OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
	List<OrderItem> orderItems;
}
// in case you get the error as follows :
// “Error executing DDL ”alter table events drop foreign key FKg0mkvgsqn8584qoql6a2rxheq“ via JDBC Statement”
// then cause is some of the table names are reserved and cannot be used, so just change the table name and it works fine