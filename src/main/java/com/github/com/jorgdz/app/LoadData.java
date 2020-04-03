package com.github.com.jorgdz.app;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.stereotype.Component;

import com.github.com.jorgdz.app.documents.Permission;
import com.github.com.jorgdz.app.documents.Role;
import com.github.com.jorgdz.app.documents.User;
import com.github.com.jorgdz.app.models.Country;
import com.github.com.jorgdz.app.models.Gender;
import com.github.com.jorgdz.app.repository.PermissionRepository;
import com.github.com.jorgdz.app.repository.RoleRepository;
import com.github.com.jorgdz.app.repository.UserRepository;

import reactor.core.publisher.Flux;

@Component
public class LoadData implements ApplicationListener<ContextRefreshedEvent>{
	
	private static final Logger log = LoggerFactory.getLogger(LoadData.class);
	
	@Autowired
	private RoleRepository roleRepo;
	
	@Autowired
	private PermissionRepository permissionRepo;
	
	@Autowired
	private UserRepository userRepo;
	
	
	@Autowired
	private ReactiveMongoTemplate mongoTemplate;
			
	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {	
		mongoTemplate.dropCollection("users").subscribe();
		mongoTemplate.dropCollection("roles").subscribe();
		mongoTemplate.dropCollection("permissions").subscribe();
		
		Flux.just(new Permission("GET_ROLES", "Obtener todos los roles de usuarios"),
				new Permission("GET_ROL_BY_ID", "Obtener un rol específico por Id"),
				new Permission("GET_PERMISSIONS", "Obtener todos los permisos"))
		.flatMap(permissionRepo::save)
		.thenMany(permissions -> {
			List<Permission> listPermissions = permissionRepo.findAll().collectList().block();
			
			Flux.just(new Role("admin", "usuario administrador", listPermissions),
					new Role("usuario", "usuario comun"),
					new Role("supervisor", "usuario supervisor de los datos"))
			.flatMap(role -> {
				role.setCreatedAt(LocalDateTime.now());
				return roleRepo.save(role);
			})
			.thenMany(roles -> {
				List<Role> listRoles = roleRepo.findByName("admin").collectList().block();
				Flux.just(new User("Jorge", "Diaz", LocalDate.parse("1994-10-31"), Gender.MASCULINO, "jdzm@outlook.es", "$2a$10$HlFUDBXs9EkVq8yXiQ5nYeHr.Nc0Ej4ATzdXx9n7kVAmqY5TyxK2q", true, new Country("Ecuador"), listRoles),
						new User("Josue", "Caballero Macías", LocalDate.parse("1994-03-10"), Gender.MASCULINO, "elsorbo@gmail.com", "$2a$10$HlFUDBXs9EkVq8yXiQ5nYeHr.Nc0Ej4ATzdXx9n7kVAmqY5TyxK2q", true, new Country("Ecuador")))
				.flatMap(userRepo::save)
				.subscribe();
			})
			.subscribe();
		})
		.subscribe(create -> log.info(create.toString()));
	}

}
