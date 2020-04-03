package com.github.com.jorgdz.app.controllers;

import org.reactivestreams.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.github.com.jorgdz.app.documents.User;
import com.github.com.jorgdz.app.exceptions.NotFoundException;
import com.github.com.jorgdz.app.services.UserService;
import com.github.com.jorgdz.app.util.AppHelper;

import reactor.core.publisher.Mono;

@RestController
@RequestMapping(AppHelper.PREFIX + "/users")
@CrossOrigin(origins = AppHelper.CROSS_ORIGIN, methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.PATCH, RequestMethod.DELETE})
public class UserController extends ApiController{
	
	@Autowired
	private UserService serviceUser;
	
	@GetMapping(produces = AppHelper.FORMAT_RESPONSE)
	Publisher<ResponseEntity<User>> index ()
	{
		return this.serviceUser.findAll()
				.map(user -> ResponseEntity.ok(user));
	}
	
	@GetMapping(value = "/{id}", produces = AppHelper.FORMAT_RESPONSE)
	Publisher<ResponseEntity<User>> show (@PathVariable String id)
	{
		Mono<User> user = this.serviceUser.findById(id);
		if(user == null)
		{
			throw new NotFoundException("No existe un usuario con Id: " + id);
		}
		
		return user.map(u -> new ResponseEntity<User>(u, HttpStatus.FOUND));
	}
}
