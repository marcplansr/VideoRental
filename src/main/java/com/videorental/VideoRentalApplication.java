package com.videorental;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class VideoRentalApplication {

	public static void main(String[] args) {
		SpringApplication.run(VideoRentalApplication.class, args);
	}

}
