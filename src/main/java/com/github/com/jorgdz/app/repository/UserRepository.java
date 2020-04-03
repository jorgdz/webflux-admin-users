package com.github.com.jorgdz.app.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.github.com.jorgdz.app.documents.User;

public interface UserRepository extends ReactiveMongoRepository<User, String>{

}
