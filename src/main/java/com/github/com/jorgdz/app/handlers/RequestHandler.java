package com.github.com.jorgdz.app.handlers;

import javax.validation.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.github.com.jorgdz.app.exceptions.BadRequestException;
import com.mongodb.Function;

import reactor.core.publisher.Mono;

@Component
public class RequestHandler {
	
	@Autowired
	private Validator validator;

    public RequestHandler(Validator validator) {
        this.validator = validator;
    }
    
    public <BODY> Mono<ServerResponse> requireValidate(Function<Mono<BODY>, Mono<ServerResponse>> block, ServerRequest request, Class<BODY> elementClass) {
        return request.bodyToMono(elementClass)
                .flatMap(body -> validator.validate(body).isEmpty() ? block.apply(Mono.just(body)) : Mono.error(new BadRequestException("Revise que los campos sean correctos")));
    }
}
