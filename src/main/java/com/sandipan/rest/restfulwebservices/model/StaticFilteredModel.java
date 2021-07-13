package com.sandipan.rest.restfulwebservices.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(value = {"password"})
public class StaticFilteredModel {
    private  String name;

    // filtered field class level or field level(will not be sent as response)
    //@JsonIgnoreProperties(value = {"password"}) defines array of ignored field
    //@JsonIgnore
    private String password;
}
