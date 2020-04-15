package com.github.com.jorgdz.app.controllers;

import java.time.Duration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.com.jorgdz.app.models.Country;
import com.github.com.jorgdz.app.services.CountryService;
import com.github.com.jorgdz.app.util.AppHelper;

import reactor.core.publisher.Flux;
import reactor.util.function.Tuple2;

@RestController
@RequestMapping(value = AppHelper.PREFIX + "/stream")
public class EventController extends ApiRestController{
	
	@Autowired
	private CountryService serviceCountry;
	
	@GetMapping(value = "/countries", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
	public Flux<Tuple2<Long, Country>> getCountries ()
	{
		Flux<Long> interval = Flux.interval(Duration.ofSeconds(1));
		Flux<Country> countries = this.serviceCountry.getAll();
		return Flux.zip(interval, countries);
	}
	
	@GetMapping(value = "/delay/countries", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
	public Flux<Country> getDelayCountries ()
	{
		Flux<Country> countries = this.serviceCountry.getAll();
		return countries.delayElements(Duration.ofMillis(500));
	}
}
