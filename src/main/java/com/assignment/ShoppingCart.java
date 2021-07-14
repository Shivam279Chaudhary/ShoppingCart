package com.assignment;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class ShoppingCart {
	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(ShoppingCart.class);
		app.setBannerMode(Banner.Mode.OFF);
		app.run(args);
	}
}
