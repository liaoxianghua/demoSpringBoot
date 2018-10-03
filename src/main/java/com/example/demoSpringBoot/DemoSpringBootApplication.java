package com.example.demoSpringBoot;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.StringHttpMessageConverter;

import java.nio.charset.Charset;

@SpringBootApplication
public class DemoSpringBootApplication {
	public static void main(String[] args) {
		SpringApplication springApplication = new SpringApplication(DemoSpringBootApplication.class);
//		springApplication.setBannerMode(Banner.Mode.OFF);
		springApplication.run(args);
	}

	@Bean
	public StringHttpMessageConverter stringHttpMessageConverter(){
		StringHttpMessageConverter stringHttpMessageConverter = new StringHttpMessageConverter(Charset.forName("UTF-8"));
		return stringHttpMessageConverter;
	}
}
