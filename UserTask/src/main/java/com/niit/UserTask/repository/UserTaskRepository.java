package com.niit.UserTask.repository;

import com.niit.UserTask.domain.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserTaskRepository extends MongoRepository<User, Integer> {

}
