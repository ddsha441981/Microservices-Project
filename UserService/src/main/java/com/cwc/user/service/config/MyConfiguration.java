package com.cwc.user.service.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class MyConfiguration {

	@Bean
	@LoadBalanced //For maintain load balance to multiple instance(microservices)
	RestTemplate restTemplate() {
		return new RestTemplate();
	}
}
