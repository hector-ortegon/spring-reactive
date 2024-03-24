package com.hbortegon.poc.circuitbreakers.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.circuitbreaker.ReactiveCircuitBreakerFactory;
import org.springframework.stereotype.Service;

import reactor.core.publisher.Mono;

@Service
public class ReactiveBreakableService {

    @Autowired
    private ReactiveCircuitBreakerFactory cbFactory;

    public Mono<String> getGreetings(String name){
        return cbFactory.create("breaker").run( doSomething(name), this::logError);
    }

    public Mono<String> doSomething(String name){
        return Mono.create(sink -> {
            
            if("hector".equals(name)){
                sink.error(new Exception());
            } else {
                sink.success(name);
            }
        });
    }

    public Mono<String> logError(Throwable e) {
    return Mono.just("Circuit Breaker got an exception");
}

}
