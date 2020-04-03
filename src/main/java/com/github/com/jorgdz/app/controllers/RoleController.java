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

import com.github.com.jorgdz.app.documents.Role;
import com.github.com.jorgdz.app.exceptions.NotFoundException;
import com.github.com.jorgdz.app.services.RoleService;
import com.github.com.jorgdz.app.util.AppHelper;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@CrossOrigin(origins = AppHelper.CROSS_ORIGIN, methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.PATCH, RequestMethod.DELETE})
@RequestMapping(value= AppHelper.PREFIX + "/roles")
@RestController
public class RoleController extends ApiController {
	
	@Autowired
	private RoleService serviceRole;
		
	@GetMapping(produces = AppHelper.FORMAT_RESPONSE)
	Publisher <ResponseEntity<Role>> index ()
	{
		Flux<Role> roles = serviceRole.findAll()
			.doOnNext(rol -> log.info(rol.getName()));
	
		return roles.map(r -> ResponseEntity.ok(r));
	}
	
	@GetMapping(value = "/{id}", produces = AppHelper.FORMAT_RESPONSE)
	Publisher <ResponseEntity<Role>> show(@PathVariable String id)
	{
		Flux<Role> roles = serviceRole.findAll();
		
		Mono<Role> role = roles.filter(r -> r.getId().equals(id))
			.next()
			.doOnNext(rol -> log.info(rol.getName()));
		
		if(!role.blockOptional().isPresent())
		{
			throw new NotFoundException("No se ha encontrado el rol con id: " + id);
		}
		
		return role.map(rolFound -> new ResponseEntity<Role>(rolFound, HttpStatus.FOUND));
	}
	
}
