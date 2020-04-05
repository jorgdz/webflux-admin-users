package com.github.com.jorgdz.app.services;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.github.com.jorgdz.app.models.Country;

import reactor.core.publisher.Flux;

@Service
public class CountryServiceImpl implements CountryService{
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Value("${rest.country}")
	private String restCountry;
	
	@Override
	public Flux<Country> getAll() {
		List<Country> listCountries = Arrays.asList(this.restTemplate.getForObject(this.restCountry, Country[].class));
		
		Flux<Country> countries = Flux.fromIterable(listCountries);
		return countries.map(c -> new Country(c.getName(), c.getTranslations()));
				//.delayElements(Duration.ofSeconds(1));
	}

}
