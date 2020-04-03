package com.github.com.jorgdz.app.services;

import com.github.com.jorgdz.app.documents.Role;

import reactor.core.publisher.Flux;

public interface RoleService {
	
	Flux<Role> findAll();
	
	Flux<Role> findAllDataDriver();
	
	Flux<Role> findAllChunked();
		
}
