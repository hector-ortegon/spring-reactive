package com.hbortegon.poc.circuitbreakers.domain;

public class DomainException extends RuntimeException {

    private final String name;

    public DomainException(String name) {
        super(name);
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
}
