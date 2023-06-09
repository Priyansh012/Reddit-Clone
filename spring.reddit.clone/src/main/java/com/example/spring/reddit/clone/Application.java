package com.example.spring.reddit.clone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableAsync;

import com.example.spring.reddit.clone.config.SwaggerConfiguration;

@SpringBootApplication
@EnableAsync
@Import(SwaggerConfiguration.class)
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
