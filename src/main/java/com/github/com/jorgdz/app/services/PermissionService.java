package com.github.com.jorgdz.app.services;

import com.github.com.jorgdz.app.documents.Permission;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface PermissionService {
	
	Flux<Permission> findAll();
	
	Mono<Permission> findById(String id);
	
}
