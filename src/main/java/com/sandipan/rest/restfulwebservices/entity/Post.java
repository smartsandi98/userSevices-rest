package com.sandipan.rest.restfulwebservices.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Post {

    public Post(Integer id, String description) {
        this.id = id;
        this.description = description;
    }

    @Id
    @GeneratedValue
    @Column(name = "post_id")
    private Integer id;

    @Column(name = "post_description")
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private UserEntity userEntity;
}
