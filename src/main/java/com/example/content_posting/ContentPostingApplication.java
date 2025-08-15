package com.example.content_posting;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
//@EnableJpaRepositories(basePackages = "com.example.content_posting.repository")
//@EntityScan(basePackages = "com.example.content_posting.models")
//@ComponentScan(basePackages = "com.example.content_posting")
public class ContentPostingApplication {

	public static void main(String[] args) {
		SpringApplication.run(ContentPostingApplication.class, args);
	}

}
