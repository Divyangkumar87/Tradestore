package com.deutsche.tradestore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import com.deutsche.tradestore.businessRules.TradeException;

@SpringBootApplication
@EnableScheduling
public class Tradestore {
	
	public static void main(String[] args) throws TradeException {
		SpringApplication.run(Tradestore.class, args);
	}
	
}
