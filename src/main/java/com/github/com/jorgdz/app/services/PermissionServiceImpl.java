package com.github.com.jorgdz.app.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.github.com.jorgdz.app.documents.Permission;
import com.github.com.jorgdz.app.repository.PermissionRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class PermissionServiceImpl implements PermissionService{

	@Autowired
	private PermissionRepository permissionRepo;
	
	@Override
	public Flux<Permission> findAll() {
		return permissionRepo.findAll(Sort.by("name").ascending());
	}

	@Override
	public Mono<Permission> findById(String id) {
		Mono<Permission> permission = permissionRepo.findById(id);
		return (permission.blockOptional().isPresent()) ? permission : null;
	}

}
