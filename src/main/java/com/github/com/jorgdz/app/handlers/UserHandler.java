package com.github.com.jorgdz.app.handlers;

import org.springframework.http.HttpStatus;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.github.com.jorgdz.app.documents.Role;
import com.github.com.jorgdz.app.documents.User;
import com.github.com.jorgdz.app.exceptions.NotFoundException;
import com.github.com.jorgdz.app.services.UserService;

import reactor.core.publisher.Mono;

public class UserHandler extends ApiHandler{
	
	private UserService serviceUser;
	
	public UserHandler(UserService serviceUser) {
		this.serviceUser = serviceUser;
	}
	
	public Mono<ServerResponse> index (ServerRequest req)
	{
		return ServerResponse.ok()
				.body(this.serviceUser.findAll(), Role.class);
	}
	
	public Mono<ServerResponse> show (ServerRequest req)
	{
		Mono<User> user = this.serviceUser.findById(req.pathVariable("id"));
		
		return user.flatMap(u -> ServerResponse.status(HttpStatus.FOUND).body(BodyInserters.fromPublisher(user, User.class)))
				.switchIfEmpty(Mono.error(new NotFoundException("No existe un usuario con Id: " + req.pathVariable("id"))));
	}
}
