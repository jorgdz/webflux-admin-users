package com.github.com.jorgdz.app.controllers;

import org.reactivestreams.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.github.com.jorgdz.app.models.Country;
import com.github.com.jorgdz.app.services.CountryService;
import com.github.com.jorgdz.app.util.AppHelper;

@CrossOrigin(origins = AppHelper.CROSS_ORIGIN, methods = {RequestMethod.GET})
@RestController
@RequestMapping(value = AppHelper.PREFIX + "/countries")
public class CountryController extends ApiController {
	
	@Autowired
	private CountryService serviceCountry;
	
	@GetMapping(produces = AppHelper.FORMAT_RESPONSE)
	Publisher<ResponseEntity<Country>> index ()
	{
		return serviceCountry.getAll().map(c -> ResponseEntity.ok(c));
	}
	
}