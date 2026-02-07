package com.web.basura;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class BasuraApplication {

	public static void main(String[] args) {
		SpringApplication.run(BasuraApplication.class, args);
	}

}
