package com.nnk.springboot;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@PropertySource("classpath:application-prod.properties")
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
		// SpringApplication.run(new Class[] { Application.class, Initializer.class }, args);
	}
}
