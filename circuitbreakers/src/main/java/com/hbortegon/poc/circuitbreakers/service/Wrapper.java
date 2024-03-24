package com.hbortegon.poc.circuitbreakers.service;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import reactor.core.publisher.Mono;

public class Wrapper {

    @CircuitBreaker(name = "apiBreaker")
    protected <T> Mono<T> protect(Mono<T> apiCall) {
        return apiCall;
    }
}
