package com.db.tf3.tradeDemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class TradeDemoApplication {

	public static void main(String[] args) {

		SpringApplication.run(TradeDemoApplication.class, args);
	}

}
