package com.fquer.springchess;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Configuration
@EnableAutoConfiguration
@ComponentScan
public class SpringChessApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringChessApplication.class, args);
	}

}
