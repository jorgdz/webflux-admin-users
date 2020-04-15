package com.github.com.jorgdz.app.controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.com.jorgdz.app.models.Country;
import com.github.com.jorgdz.app.services.CountryService;
import com.github.com.jorgdz.app.util.AppHelper;

import reactor.core.publisher.Flux;

@RestController
@RequestMapping(value = AppHelper.PREFIX + "/countries")
public class CountryRestController {
	
	@Autowired
	private CountryService serviceCountry;
	
	@GetMapping(produces = AppHelper.FORMAT_RESPONSE)
	public ResponseEntity<Flux<Country>> index ()
	{
		Flux<Country> countries = this.serviceCountry.getAll();
		return ResponseEntity.status(HttpStatus.OK).body(countries);
	}
}
