package com.hbortegon.poc.circuitbreakers;

import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.hbortegon.poc.circuitbreakers.service.BreakableService;

import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import reactor.test.StepVerifier;

@ExtendWith({SpringExtension.class})
@SpringBootTest(classes = CircuitbreakersApplication.class)
public class BreakableServiceTest {

    @Autowired
    private CircuitBreakerRegistry registry;

    @Autowired
    BreakableService realService;

    @BeforeEach
    public void setUp(){
        registry.circuitBreaker("testCircuitBreaker").reset();
    }

    @Test
    void shouldThrowCustomExceptionWhenCircuitBreakerOpen(){
        registry.circuitBreaker("testCircuitBreaker").transitionToOpenState();
        StepVerifier.create(realService.getGreetings("Jhon"))
        .expectError().verify();
    }

}
