package com.gokhan.veteriner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

@PropertySources({
	@PropertySource("classpath:application.properties"),
	@PropertySource("classpath:secure.properties")
})

@SpringBootApplication
public class VeterinerApplication {

	public static void main(String[] args) {
		SpringApplication.run(VeterinerApplication.class, args);
	}

}

