package com.github.com.jorgdz.app.handlers;

import org.springframework.http.HttpStatus;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.github.com.jorgdz.app.documents.Role;
import com.github.com.jorgdz.app.exceptions.NotFoundException;
import com.github.com.jorgdz.app.services.RoleService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class RoleHandler extends ApiHandler {
	
	private RoleService serviceRole;
	
	public RoleHandler(RoleService serviceRole) {
		this.serviceRole = serviceRole;
	}
	
	public Mono <ServerResponse> index (ServerRequest req)
	{
		Flux<Role> roles = serviceRole.findAll()
			.doOnNext(rol -> log.info(rol.getName()));
		
		return ServerResponse.ok()
				.body(roles, Role.class);
	}
	
	public Mono <ServerResponse> show(ServerRequest req)
	{
		Flux<Role> roles = serviceRole.findAll();
		
		Mono<Role> role = roles.filter(r -> r.getId().equals(req.pathVariable("id")))
			.next()
			.doOnNext(rol -> log.info(rol.getName()));
		
		return role.flatMap(r -> ServerResponse.status(HttpStatus.FOUND)
				.body(BodyInserters.fromPublisher(role, Role.class)))
				.switchIfEmpty(Mono.error(new NotFoundException("No se ha encontrado el rol con id: " + req.pathVariable("id"))));
	}
	
}
