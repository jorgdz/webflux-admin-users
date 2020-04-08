package com.github.com.jorgdz.app;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.config.CorsRegistry;
import org.springframework.web.reactive.config.EnableWebFlux;
import org.springframework.web.reactive.config.WebFluxConfigurer;

import com.github.com.jorgdz.app.util.AppHelper;

@Configuration
@EnableWebFlux
public class CorsConfig implements WebFluxConfigurer{
	
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**")
			.allowedOrigins(AppHelper.CROSS_ORIGIN)
			.allowCredentials(true)
			.allowedMethods("*")
            .allowedHeaders("*");
	}
}
