package com.niit.UserTask.service;

import com.niit.UserTask.domain.Task;
import com.niit.UserTask.domain.User;

import java.util.List;

public interface IUserTaskService {
    User saveUser (User user);
    User addTask (User user);
    List<User> getAllUsers ();
    User getAllTasks ();
    User getUserById (int UserId);
    Task getTaskById (int userId, int taskId);
    boolean deleteAllUser ();
    boolean deleteUserById (int userId);
    boolean deleteTaskById (int taskId);

}
