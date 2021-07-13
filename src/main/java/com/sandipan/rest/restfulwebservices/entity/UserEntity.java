package com.sandipan.rest.restfulwebservices.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "user")
public class UserEntity {

    public UserEntity(String name, Date birthDate) {
        this.name = name;
        this.birthDate = birthDate;
    }

    @Id
    @GeneratedValue
    @Column(name = "user_id")
    private Integer id;

    @Size(min = 5, message = "name should have atleast 5 characters ")
    @Column(name = "user_name")
    private String name;

    @Past
    @Column(name = "user_birth_date")
    private Date birthDate;

    @OneToMany(mappedBy = "userEntity")
    private List<Post> posts;

}
