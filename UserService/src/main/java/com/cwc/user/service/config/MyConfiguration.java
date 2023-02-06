package com.cwc.user.service.config;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class MyConfiguration {

	@Bean
	@LoadBalanced //For maintain load balance to multiple instance(micro services)
	public RestTemplate restTemplate() {
	    return new RestTemplate();
	}
}
