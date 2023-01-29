package ru.students.airline;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class AirlineApp implements WebMvcConfigurer {

	public static void main(String[] args) {
		SpringApplication.run(AirlineApp.class, args);
	}

	@Bean
	ModelMapper modelMapper() {
		return new ModelMapper();
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/**").addResourceLocations("src/main/resources");
	}
}
