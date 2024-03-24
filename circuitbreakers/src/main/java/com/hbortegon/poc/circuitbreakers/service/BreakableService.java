package com.hbortegon.poc.circuitbreakers.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.circuitbreaker.ReactiveCircuitBreakerFactory;
import org.springframework.stereotype.Service;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import reactor.core.publisher.Mono;

@Service
public class BreakableService {


    @CircuitBreaker(name = "circuit")
    public Mono<String> getGreetings(String name){
        return Mono.just(name)
        .doOnError(this::handleError);
    }



    public Mono<String> handleError(Throwable e) {
        return Mono.error(e);
    }

    private void logError(Throwable e) {
        System.out.println("Exception ocurrs in service");
        e.printStackTrace();
    }

}
