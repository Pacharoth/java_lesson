package com.ecommerce.ecommerce.user;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User,Integer> {
    List<User> findAll();
    
    Optional<User> findByNameContaining(String name);
}
