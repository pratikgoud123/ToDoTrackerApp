package com.niit.UserTask.service;

import com.niit.UserTask.domain.User;

import java.util.List;

public interface IUserTaskService {
    User saveUser (User user);
    List<User> getAllUsers ();
}
