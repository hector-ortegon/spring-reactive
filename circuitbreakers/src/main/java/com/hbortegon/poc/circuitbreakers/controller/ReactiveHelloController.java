package com.hbortegon.poc.circuitbreakers.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hbortegon.poc.circuitbreakers.service.BreakableService;
import com.hbortegon.poc.circuitbreakers.service.ReactiveBreakableService;

import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/hello")
public class ReactiveHelloController {

    private final BreakableService breakableService;
    private final ReactiveBreakableService reactiveBreakableService;
    

    public ReactiveHelloController(BreakableService breakableService, ReactiveBreakableService reactiveBreakableService){
        this.breakableService = breakableService;
        this.reactiveBreakableService = reactiveBreakableService;
    }


    @GetMapping("/jhon")
    public Mono<String> sayHello(@RequestParam(name = "name") String name) {
        return breakableService.getGreetings(name)
                                .map(message -> String.join(" ", message, name));
    }

    @GetMapping("hector")
    public Mono<String> sayHelloToHector(@RequestParam(name = "name") String name) {
        return reactiveBreakableService.getGreetings(name)
                                .map(message -> String.join(" ", message, name));
    }

}
