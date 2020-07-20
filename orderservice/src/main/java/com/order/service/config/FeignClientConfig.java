package com.order.service.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import feign.Request;
import feign.auth.BasicAuthRequestInterceptor;
import feign.codec.Encoder;
import feign.form.FormEncoder;

@Configuration
public class FeignClientConfig {

	@Bean
	public Encoder feignFormEncoder() {
		return new FormEncoder();
	}

	@Bean
	public BasicAuthRequestInterceptor basicAuthRequestInterceptor() {
		return new BasicAuthRequestInterceptor("admin", "admin");
	}
	
	@Bean 
	public Request.Options timeoutConfiguration(){
	  return new Request.Options(5000, 30000);
	}

}
