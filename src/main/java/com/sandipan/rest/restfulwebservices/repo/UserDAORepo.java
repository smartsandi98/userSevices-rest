package com.sandipan.rest.restfulwebservices.repo;

import com.sandipan.rest.restfulwebservices.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDAORepo extends JpaRepository<UserEntity, Integer> {
}
