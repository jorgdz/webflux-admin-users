package com.github.com.jorgdz.app.documents;

import java.time.LocalDateTime;
import java.util.List;

import javax.validation.constraints.NotEmpty;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(content = Include.NON_NULL)
@Document(collection = "roles")
public class Role {
	
	@Id
	private String id;
	
	@NotEmpty(message = "El campo 'nombre del rol' es obligatorio.")
	private String name;
	
	private String description;
	
	@DateTimeFormat(iso=ISO.DATE_TIME)
	private LocalDateTime createdAt;
	
	@DBRef(lazy = true)
	private List<Permission> permissions;
	
	public Role(String name, String description, List<Permission> permissions) {
		this.name = name;
		this.description = description;
		this.permissions = permissions;
	}
	
	public Role(String name, String description)
	{
		this.name = name;
		this.description = description;
	}
	
	public Role() {}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public List<Permission> getPermissions() {
		return permissions;
	}

	public void setPermissions(List<Permission> permissions) {
		this.permissions = permissions;
	}

	@Override
	public String toString() {
		return "Role [id=" + id + ", name=" + name + ", description=" + description + ", createdAt=" + createdAt + "]";
	}
	
}
