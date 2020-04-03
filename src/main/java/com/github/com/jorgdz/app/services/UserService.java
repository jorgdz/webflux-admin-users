package com.github.com.jorgdz.app.services;

import com.github.com.jorgdz.app.documents.User;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface UserService {
	
	Flux<User> findAll();
	
	Mono<User> findById(String id);
	
}
