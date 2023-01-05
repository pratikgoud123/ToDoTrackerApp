package com.niit.UserTask.repository;

import com.niit.UserTask.domain.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserTaskRepository extends MongoRepository<User, Integer> {

    List<User> findByEmailId (String emailId);

    @Query("{'tasks.taskId': ?0}")
    User findByTaskId (int taskId);

}
