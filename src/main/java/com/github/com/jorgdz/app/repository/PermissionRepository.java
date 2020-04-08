package com.github.com.jorgdz.app.repository;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.github.com.jorgdz.app.documents.Permission;

import reactor.core.publisher.Mono;

public interface PermissionRepository extends ReactiveMongoRepository<Permission, String>{
	
	@Query("{ 'name': ?0 }")
	Mono<Permission> findByName (String name);
}
