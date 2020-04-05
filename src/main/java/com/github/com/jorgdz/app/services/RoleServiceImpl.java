package com.github.com.jorgdz.app.services;

import java.time.Duration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.github.com.jorgdz.app.documents.Role;
import com.github.com.jorgdz.app.repository.RoleRepository;

import reactor.core.publisher.Flux;

@Service
public class RoleServiceImpl implements RoleService{
	
	@Autowired
	private RoleRepository repoRole;
	
	@Override
	public Flux<Role> findAll() {
		return repoRole.findAll(Sort.by("name").ascending())
			.map(role -> {
				role.setName(role.getName().toUpperCase());
				role.setDescription(role.getDescription().toUpperCase());
				return role;
			});
	}

	@Override
	public Flux<Role> findAllDataDriver() {
		return repoRole.findAll(Sort.by("name").ascending())
			.map(role -> {
				role.setName(role.getName().toUpperCase());
				role.setDescription(role.getDescription().toUpperCase());
				return role;
			})
			.delayElements(Duration.ofSeconds(1));
	}

	@Override
	public Flux<Role> findAllChunked() {
		return repoRole.findAll(Sort.by("name").ascending())
			.map(role -> {
				role.setName(role.getName().toUpperCase());
				role.setDescription(role.getDescription().toUpperCase());
				return role;
			})
			.repeat(5000);
	}

}
