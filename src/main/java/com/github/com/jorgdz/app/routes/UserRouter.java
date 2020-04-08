package com.github.com.jorgdz.app.routes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.github.com.jorgdz.app.handlers.UserHandler;
import com.github.com.jorgdz.app.services.UserService;
import com.github.com.jorgdz.app.util.AppHelper;

@Configuration
public class UserRouter {
	
	@Autowired
	private UserService serviceUser;
	
	@Bean
	public RouterFunction<ServerResponse> routeUser ()
	{
		UserHandler userHandler = new UserHandler(serviceUser);
		
		return RouterFunctions
				.route(RequestPredicates.GET(AppHelper.PREFIX.concat("/users")).and(RequestPredicates.accept(MediaType.APPLICATION_JSON)), userHandler::index)
				.andRoute(RequestPredicates.GET(AppHelper.PREFIX.concat("/users/{id}")).and(RequestPredicates.accept(MediaType.APPLICATION_JSON)), userHandler::show);
	}
}
