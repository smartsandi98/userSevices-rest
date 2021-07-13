package com.sandipan.rest.restfulwebservices.model;

import com.fasterxml.jackson.annotation.JsonFilter;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonFilter("DynamicFilter")
public class DynamicFilteredModel {
    private  String name;
    private String password;
}
