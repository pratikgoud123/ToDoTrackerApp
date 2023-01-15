package com.niit.UserAuthentication.repository;

import com.niit.UserAuthentication.domain.User;
import com.niit.UserAuthentication.exception.UserNotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    User findByEmailIdAndPassword(String emailId, String password) throws UserNotFoundException;
}
