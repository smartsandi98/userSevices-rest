package com.sandipan.rest.restfulwebservices.controller;

import com.sandipan.rest.restfulwebservices.model.Hello;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@NoArgsConstructor
public class Controller {

    private MessageSource messageSource;

    @Autowired
    public Controller(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    //http://localhost:8080/hello-world
    @GetMapping("/hello-world")
    public String helloWorld() {
        return "Hello World";
    }

    //http://localhost:8080/hello-world-bean
    @GetMapping("/hello-world-bean")
    public Hello helloWorldBean() {
        return new Hello("Hello World");
    }
   //http://localhost:8080/hello-world-bean/path-variable/sandipan
    @GetMapping("/hello-world-bean/path-variable/{name}")
    public Hello helloWorldPathVariable(@PathVariable String name) {
        return new Hello(String.format("Hello , %s", name));
    }

    //http://localhost:8080//hello-world-Internationalized
    @GetMapping("/hello-world-Internationalized")
    public String helloWorldInternationalized(
           // @RequestHeader(name = "Accept-Language", required = false) Locale locale
    ) {
        //return "Hello World";
        return messageSource.getMessage("good.morning", null, "Default Message", /*locale*/ LocaleContextHolder.getLocale());
    }
}
