package com.niit.UserTask.service;

import com.niit.UserTask.domain.Task;
import com.niit.UserTask.domain.User;
import com.niit.UserTask.exception.TaskNotFoundException;
import com.niit.UserTask.exception.UserAlreadyExistsException;
import com.niit.UserTask.exception.UserNotFoundException;

import java.util.List;
import java.util.Optional;

public interface IUserTaskService {
    User saveUser (User user) throws UserAlreadyExistsException;
    User addTask (int userId, Task task);
    User updateTask (int userId, Task task);
    List<User> getAllUsers ();
    List<Task> getAllTasksOfUser (int userId);
    Optional<User> getUserById (int userId) throws UserNotFoundException;
    List<User> getUserByEmailId (String emailId) throws UserNotFoundException;
    User getByTaskId (int userId, int taskId) throws TaskNotFoundException;
    boolean deleteAllUser ();
    boolean deleteUserById (int userId) throws UserNotFoundException;
    boolean deleteTaskById (int taskId) throws TaskNotFoundException;

}
