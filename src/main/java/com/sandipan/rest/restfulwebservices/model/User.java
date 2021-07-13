package com.sandipan.rest.restfulwebservices.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class User {


    private Integer id;

    @Size(min = 5, message = "name should have atleast 5 characters ")
    private String name;

    @Past
    private Date birthDate;

}
