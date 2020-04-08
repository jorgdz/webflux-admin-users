package com.github.com.jorgdz.app.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.github.com.jorgdz.app.documents.User;
import com.github.com.jorgdz.app.repository.UserRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserRepository userRepo; 
	
	@Override
	public Flux<User> findAll() {
		return userRepo.findAll(Sort.by("surname", "name").ascending());
	}

	@Override
	public Mono<User> findById(String id) {
		Mono<User> user = this.userRepo.findById(id);
		return user;
	}

}
