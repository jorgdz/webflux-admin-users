package com.github.com.jorgdz.app.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.com.jorgdz.app.documents.Permission;
import com.github.com.jorgdz.app.exceptions.ConflictException;
import com.github.com.jorgdz.app.exceptions.NotFoundException;
import com.github.com.jorgdz.app.services.PermissionService;
import com.github.com.jorgdz.app.util.AppHelper;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(value = AppHelper.PREFIX + "/permissions")
public class PermissionRestController extends ApiRestController {
	
	@Autowired
	private PermissionService servicePermission;
	
	@GetMapping(produces = AppHelper.FORMAT_RESPONSE)
	public ResponseEntity<Flux<Permission>> index ()
	{
		Flux<Permission> permissions = this.servicePermission.findAll();
		return ResponseEntity.ok().body(permissions);
	}
	
	@GetMapping(value = "/{id}", produces = AppHelper.FORMAT_RESPONSE)
	public Mono<ResponseEntity<Permission>> show (@PathVariable(name = "id") String id)
	{
		Mono<Permission> permission = this.servicePermission.findById(id);
		
		return permission.map(p -> ResponseEntity.status(HttpStatus.FOUND).body(p))
	            .switchIfEmpty(Mono.error(new NotFoundException("No existe permiso con Id " + id))); 
	}
	
	@PostMapping(produces = AppHelper.FORMAT_RESPONSE)
	public Mono<?> store (@Valid @RequestBody Permission permission)
	{	
		Mono<Permission> find = this.servicePermission.findByName(permission.getName());
		
		return find.flatMap(existingPermission -> Mono.error(new ConflictException("El permiso '" + permission.getName() + "' ya existe")))
				.switchIfEmpty(this.servicePermission.save(permission).map(p -> ResponseEntity.status(HttpStatus.CREATED).body(p)));
	}
}
