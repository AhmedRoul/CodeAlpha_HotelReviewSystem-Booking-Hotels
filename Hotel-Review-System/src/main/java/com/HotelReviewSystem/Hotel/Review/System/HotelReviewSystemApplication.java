package com.HotelReviewSystem.Hotel.Review.System;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
//@EnableScheduling

public class HotelReviewSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(HotelReviewSystemApplication.class, args);
	}


}
