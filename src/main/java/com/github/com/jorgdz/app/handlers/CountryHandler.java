package com.github.com.jorgdz.app.handlers;

import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.github.com.jorgdz.app.models.Country;
import com.github.com.jorgdz.app.services.CountryService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class CountryHandler extends ApiHandler {
	
	private CountryService serviceCountry;
	
	public CountryHandler(CountryService serviceCountry) {
		this.serviceCountry = serviceCountry;
	}
	
	public Mono<ServerResponse> index (ServerRequest req)
	{
		Flux<Country> countries = serviceCountry.getAll();
	
		return ServerResponse.ok()
				.body(countries, Country.class);
	}
}
