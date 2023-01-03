package com.niit.repository;

import com.niit.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImpRepository extends MongoRepository<User,Integer> {
}
