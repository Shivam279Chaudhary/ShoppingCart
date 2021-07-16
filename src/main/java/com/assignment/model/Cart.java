package com.assignment.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Cart {
	@Id
//	@GeneratedValue(strategy = GenerationType.AUTO)
	private int cartId;
	private int customerId;
	@OneToMany(mappedBy = "cart", orphanRemoval=true, cascade = CascadeType.ALL)
	List<CartItem> cartItems;

	public void placeOrder(int cartId) {
		// logic
		System.out.println("Your Order is placed successfully");
	}

	public int totalAmount() {
		
		if(this.cartItems==null)
			return 0;
		
		return this.cartItems
				   .stream()
				   .map(e -> e.getQuantity() * e.getPricePerUnit())
				   .reduce(0, (a, b) -> a + b);
	}
}
