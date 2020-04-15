package com.github.com.jorgdz.app.routes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.github.com.jorgdz.app.handlers.RoleHandler;
import com.github.com.jorgdz.app.services.RoleService;
import com.github.com.jorgdz.app.util.AppHelper;

@Configuration
public class RoleRouter {
	
	@Autowired
	private RoleService serviceRole;
	
	@Bean
	public RouterFunction<ServerResponse> routeRole ()
	{
		RoleHandler roleHandler = new RoleHandler(serviceRole);
		
		return RouterFunctions
				.route(RequestPredicates.GET(AppHelper.PREFIX.concat("/handler/roles")).and(RequestPredicates.accept(MediaType.APPLICATION_JSON)), roleHandler::index)
				.andRoute(RequestPredicates.GET(AppHelper.PREFIX.concat("/handler/roles/{id}")).and(RequestPredicates.accept(MediaType.APPLICATION_JSON)), roleHandler::show);
	}
	
}
