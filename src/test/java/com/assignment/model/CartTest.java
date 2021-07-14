package com.assignment.model;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CartTest {
	// requirement : add item to cart and check if it exists 

	@Test
	void emptyCartWhenNewCartCreated() {
		Cart cart = new Cart();
		assertAll(
				() -> assertEquals(cart.totalAmount(),0),
				() -> assertNull(cart.getCartItems())
			);
	}
}
