package com.example.KPzuulproxy.dto;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.web.client.RestTemplate;

public class RestemplateLoadBalanced {

	
	public RestemplateLoadBalanced() {
		
	}
	
	@LoadBalanced
	private RestTemplate rest = new RestTemplate();
	
	
}
