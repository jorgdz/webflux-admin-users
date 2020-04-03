package com.github.com.jorgdz.app.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.github.com.jorgdz.app.documents.Permission;

public interface PermissionRepository extends ReactiveMongoRepository<Permission, String>{

}
