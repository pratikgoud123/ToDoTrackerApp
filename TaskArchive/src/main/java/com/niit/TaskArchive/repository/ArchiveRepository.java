package com.niit.TaskArchive.repository;

import com.niit.TaskArchive.domain.Task;
import com.niit.TaskArchive.domain.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArchiveRepository extends MongoRepository<User, Integer> {
}
