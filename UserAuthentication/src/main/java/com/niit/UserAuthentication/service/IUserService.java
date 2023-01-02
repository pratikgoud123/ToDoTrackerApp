package com.niit.UserAuthentication.service;

import com.niit.UserAuthentication.domain.User;
import com.niit.UserAuthentication.exception.UserAlreadyExistsException;
import com.niit.UserAuthentication.exception.UserNotFoundException;

import java.util.List;

public interface IUserService {

    User addUser(User user) throws UserAlreadyExistsException;

    User loginCheck(String emailId, String password) throws UserNotFoundException;

    List<User> getAllUsers();

    public boolean deleteUserById(int userId);
}
