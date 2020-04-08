package com.github.com.jorgdz.app.handlers;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.github.com.jorgdz.app.documents.Permission;
import com.github.com.jorgdz.app.exceptions.NotFoundException;
import com.github.com.jorgdz.app.services.PermissionService;

import reactor.core.publisher.Mono;

public class PermissionHandler extends ApiHandler {
	
	private RequestHandler requestHandler;
	
	private PermissionService servicePermission;
	
	public PermissionHandler(RequestHandler requestHandler, PermissionService servicePermission) {
		this.servicePermission = servicePermission;
		this.requestHandler = requestHandler;
	}
	
	public Mono<ServerResponse> index (ServerRequest request)
	{		
		return ServerResponse.ok()
				.body(servicePermission.findAll(), Permission.class);
	}
	
	public Mono<ServerResponse> show (ServerRequest request)
	{
		String id = request.pathVariable("id");
		Mono<Permission> permission = this.servicePermission.findById(id);
	
		return permission.flatMap(p -> ServerResponse.status(HttpStatus.FOUND)
            .body(BodyInserters.fromPublisher(permission, Permission.class)))
			.switchIfEmpty(Mono.error(new NotFoundException("No se ha encontrado el permiso con Id: " + id)));
    }
	
	public Mono<ServerResponse> store (ServerRequest request)
	{
		return requestHandler.requireValidate(body -> {
	
			Mono<Permission> permission = body.flatMap(p -> this.servicePermission.save(p));
            
            return ServerResponse.status(HttpStatus.CREATED)
				.contentType(MediaType.APPLICATION_JSON)
				.body(permission, Permission.class);
            
        }, request, Permission.class);
	}
}
