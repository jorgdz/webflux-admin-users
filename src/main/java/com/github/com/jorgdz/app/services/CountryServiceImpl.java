package com.github.com.jorgdz.app.services;

import java.time.Duration;
import java.time.Instant;
/*import java.util.Arrays;
import java.util.List;*/

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
//import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import com.github.com.jorgdz.app.models.Country;

import reactor.core.publisher.Flux;

@Service
public class CountryServiceImpl implements CountryService{
	
	private final Logger log = LoggerFactory.getLogger(CountryServiceImpl.class);
		
	@Value("${rest.country}")
	private String restCountry;
	
	@Override
	public Flux<Country> getAll() {
		Instant start = Instant.now();
		
		WebClient client = WebClient.create(this.restCountry);
		
		//RestTemplate restTemplate = new RestTemplate();
				
		Flux<Country> countries = client.get()
				.uri("/all")
				.accept(MediaType.APPLICATION_JSON)
				.retrieve()
				.bodyToFlux(Country.class); 
		
		/*List<Country> listCountries = Arrays.asList(restTemplate.getForObject(this.restCountry, Country[].class));
		Flux<Country> countries = Flux.fromIterable(listCountries);*/
	
		timerLog(start);
		
		return countries;
	}
	
	private void timerLog (Instant start)
	{
		log.info("Tiempo transcurrido: " + Duration.between(start, Instant.now()).toMillis() + "ms");
	}
}
