package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;

@SpringBootApplication(exclude=DataSourceAutoConfiguration.class)
@EnableWebSecurity
public class Demo5Application {

	@Bean
    public RedirectStrategy redirectStrategy() {
    	return new DefaultRedirectStrategy();
    }
	
	public static void main(String[] args) {
		SpringApplication.run(Demo5Application.class, args);
	}

}
