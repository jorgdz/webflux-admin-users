package com.github.com.jorgdz.app.exceptions;

import java.util.Map;

import org.springframework.boot.autoconfigure.web.ResourceProperties;
import org.springframework.boot.autoconfigure.web.reactive.error.AbstractErrorWebExceptionHandler;
import org.springframework.boot.web.reactive.error.ErrorAttributes;
import org.springframework.context.ApplicationContext;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.codec.ServerCodecConfigurer;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import reactor.core.publisher.Mono;

@Component
@Order(-2)
public class Handler  extends AbstractErrorWebExceptionHandler {

	public Handler(ErrorAttributes errorAttributes, ResourceProperties resourceProperties,
			ApplicationContext applicationContext, ServerCodecConfigurer config) {
		
		super(errorAttributes, resourceProperties, applicationContext);
		this.setMessageWriters(config.getWriters());
		this.setMessageReaders(config.getReaders());
	}

	@Override
	protected RouterFunction<ServerResponse> getRoutingFunction(ErrorAttributes errorAttributes) {
		return RouterFunctions.route(RequestPredicates.all(), req -> this.renderErrorResponse(req));
	}
	
	private Mono<ServerResponse> renderErrorResponse(ServerRequest request) 
	{
		Map<String, Object> map = getErrorAttributes(request, false);
		
		return ServerResponse.status(HttpStatus.BAD_REQUEST)
	         .contentType(MediaType.APPLICATION_JSON)
	         .body(BodyInserters.fromValue(map));
    }
}
