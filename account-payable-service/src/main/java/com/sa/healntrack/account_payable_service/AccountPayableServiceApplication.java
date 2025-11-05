package com.sa.healntrack.account_payable_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
@EnableFeignClients
@SpringBootApplication
public class AccountPayableServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(AccountPayableServiceApplication.class, args);
	}

}
