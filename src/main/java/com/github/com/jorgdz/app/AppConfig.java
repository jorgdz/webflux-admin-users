package com.github.com.jorgdz.app;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.web.client.RestTemplate;

@Configuration
@PropertySources(
	@PropertySource("classpath:env.properties")
)
public class AppConfig {
	
	@Bean("clienteRest")
	public RestTemplate registrarRestTemplate()
	{
		return new RestTemplate();
	}
	
}
