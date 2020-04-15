package com.github.com.jorgdz.app.routes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.github.com.jorgdz.app.handlers.PermissionHandler;
import com.github.com.jorgdz.app.handlers.RequestHandler;
import com.github.com.jorgdz.app.services.PermissionService;
import com.github.com.jorgdz.app.util.AppHelper;

@Configuration
public class PermissionRouter {
	
	@Autowired
	private RequestHandler requestHandler;
	
	@Autowired
	private PermissionService servicePermission;
	
	@Bean
	public RouterFunction<ServerResponse> routePermission ()
	{
		PermissionHandler permissionHandler = new PermissionHandler(requestHandler, servicePermission);
		
		return RouterFunctions
				.route(RequestPredicates.GET(AppHelper.PREFIX.concat("/handler/permissions")).and(RequestPredicates.accept(MediaType.APPLICATION_JSON)), permissionHandler::index)
				.andRoute(RequestPredicates.GET(AppHelper.PREFIX.concat("/handler/permissions/{id}")).and(RequestPredicates.accept(MediaType.APPLICATION_JSON)), permissionHandler::show)
				.andRoute(RequestPredicates.POST(AppHelper.PREFIX.concat("/handler/permissions")).and(RequestPredicates.accept(MediaType.APPLICATION_JSON)), permissionHandler::store);
	}
}
