package com.github.com.jorgdz.app.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.github.com.jorgdz.app.documents.Role;

import reactor.core.publisher.Flux;

public interface RoleRepository extends ReactiveMongoRepository<Role, String>{
	
	Flux<Role> findByName(String name);
	
}
