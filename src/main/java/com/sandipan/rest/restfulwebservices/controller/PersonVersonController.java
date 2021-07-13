package com.sandipan.rest.restfulwebservices.controller;

import com.sandipan.rest.restfulwebservices.model.Name;
import com.sandipan.rest.restfulwebservices.model.PersonV1;
import com.sandipan.rest.restfulwebservices.model.PersonV2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//versioning
@RestController
@RequestMapping("/versioning")
public class PersonVersonController {

    //URI versioning
    @GetMapping("/v1/p1")
    public PersonV1 personV1(){
        return new PersonV1("Bob");
    }

    @GetMapping("/v2/p2")
    public PersonV2 personV2(){
        return new PersonV2(new Name("Bob", "Builder"));
    }
    // using request parameter to differentiate between 2 version
    @GetMapping(value = "/p1/param", params = "version=1")
    public PersonV1 personV1Param(){
        return new PersonV1("Bob");
    }

    @GetMapping(value = "/p2/param", params = "version=2 ")
    public PersonV2 personV2Param(){
        return new PersonV2(new Name("Bob", "Builder"));
    }

    // using header param to differentiate between 2 version(header versioning)
    // Also called Header type versioning
    @GetMapping(value = "/p1/header", headers = "X-API-VERSION=1")
    public PersonV1 personV1HeaderParam(){
        return new PersonV1("Bob");
    }

    @GetMapping(value = "/p2/header", headers = "X-API-VERSION=2")
    public PersonV2 personV2HeaderParam(){
        return new PersonV2(new Name("Bob", "Builder"));
    }

    // using produces header parameter to differentiate between 2 version(header versioning)
    //different than using normal header parameter
    //produces is saying what is output of this specific service
    //This is also called accept header and MIME(media) type and content negotiation versioning
    @GetMapping(value = "/p1/produces", produces = "application/vnd.company.app-v1+json")
    public PersonV1 personV1ProducesHeaderParam(){
        return new PersonV1("Bob");
    }

    @GetMapping(value = "/p2/produces", produces = "application/vnd.company.app-v2+json")
    public PersonV2 personV2ProducesHeaderParam(){
        return new PersonV2(new Name("Bob", "Builder"));
    }
}
