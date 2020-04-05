package com.github.com.jorgdz.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.github.com.jorgdz.app.documents.Permission;
import com.github.com.jorgdz.app.exceptions.NotFoundException;
import com.github.com.jorgdz.app.services.PermissionService;
import com.github.com.jorgdz.app.util.AppHelper;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@CrossOrigin(origins = AppHelper.CROSS_ORIGIN, methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.PATCH, RequestMethod.DELETE})
@RequestMapping(value= AppHelper.PREFIX + "/permissions")
@RestController
public class PermissionController extends ApiController {
	
	@Autowired
	private PermissionService servicePermission;
	
	@GetMapping
	public Flux<Permission> index ()
	{		
		return servicePermission.findAll();
	}
	
	@GetMapping(value = "/{id}", produces = AppHelper.FORMAT_RESPONSE)
	public Mono<ResponseEntity<Permission>> show (@PathVariable String id)
	{
		Mono<Permission> permission = this.servicePermission.findById(id);
		if(permission == null)
		{
			throw new NotFoundException("No se ha encontrado el permiso con Id: " + id);
		}
		
		return permission.map(p -> new ResponseEntity<Permission>(p, HttpStatus.FOUND));
	}
}
