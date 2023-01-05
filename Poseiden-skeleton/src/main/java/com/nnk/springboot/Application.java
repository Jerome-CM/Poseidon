package com.nnk.springboot;

import com.nnk.springboot.config.Initializer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(new Class[] { Application.class, Initializer.class }, args);
	}
}
