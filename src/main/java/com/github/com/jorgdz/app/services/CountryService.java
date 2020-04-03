package com.github.com.jorgdz.app.services;

import com.github.com.jorgdz.app.models.Country;

import reactor.core.publisher.Flux;

public interface CountryService {
	
	Flux<Country> getAll ();
	
}
