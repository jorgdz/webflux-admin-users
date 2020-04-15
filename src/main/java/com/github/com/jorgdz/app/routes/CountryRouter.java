package com.github.com.jorgdz.app.routes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.github.com.jorgdz.app.handlers.CountryHandler;
import com.github.com.jorgdz.app.services.CountryService;
import com.github.com.jorgdz.app.util.AppHelper;

@Configuration
public class CountryRouter {
	
	@Autowired
	private CountryService serviceCountry;
	
	@Bean
	public RouterFunction<ServerResponse> routeCountry ()
	{
		CountryHandler countryHandler = new CountryHandler(serviceCountry);
		
		return RouterFunctions
				.route(RequestPredicates.GET(AppHelper.PREFIX.concat("/handler/countries")).and(RequestPredicates.accept(MediaType.APPLICATION_JSON)), countryHandler::index);
	}
}
