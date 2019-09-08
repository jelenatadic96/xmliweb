package com.megatravel;

import java.util.Collections;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorViewResolver;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.ModelAndView;

@SpringBootApplication
@EnableFeignClients
@EnableEurekaClient
public class FrontApplication {

	// All the resources that cannot be found on server are redirected to
	// index.html. Angular will handle the routing for them.
	@Bean
	ErrorViewResolver supportPathBasedLocationStrategyWithoutHashes() {
		return new ErrorViewResolver() {

			@Override
			public ModelAndView resolveErrorView(HttpServletRequest request, HttpStatus status,
					Map<String, Object> model) {
				return status == HttpStatus.NOT_FOUND
						? new ModelAndView("index.html", Collections.<String, Object>emptyMap(), HttpStatus.OK)
						: null;
			}
		};
	}

	public static void main(String[] args) {
		SpringApplication.run(FrontApplication.class, args);
	}
}