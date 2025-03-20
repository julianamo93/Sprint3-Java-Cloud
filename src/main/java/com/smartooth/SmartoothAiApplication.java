package com.smartooth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.smartooth")
public class SmartoothAiApplication {

	public static void main(String[] args) {
		SpringApplication.run(SmartoothAiApplication.class, args);
	}

}
