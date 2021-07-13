package com.sandipan.rest.restfulwebservices.model;

import lombok.Getter;
import lombok.Setter;

public class Hello {

    @Getter
    @Setter
    private String message;

    public Hello(String message) {
        this.message = message;
    }
}
