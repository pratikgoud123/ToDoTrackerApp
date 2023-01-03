package com.niit.UserTask.service;

import com.niit.UserTask.domain.Task;
import com.niit.UserTask.domain.User;
import com.niit.UserTask.exception.TaskNotFoundException;
import com.niit.UserTask.exception.UserAlreadyExistsException;
import com.niit.UserTask.exception.UserNotFoundException;

import java.util.List;

public interface IUserTaskService {
    User saveUser (User user) throws UserAlreadyExistsException;
    User addTask (int userId, Task task);
    User updateTask (int userId, Task task);
    List<User> getAllUsers ();
    List<Task> getAllTasksOfUser (int userId);
    User getUserById (int userId) throws UserNotFoundException;
    Task getTaskById (int userId, int taskId) throws TaskNotFoundException;
    boolean deleteAllUser ();
    boolean deleteUserById (int userId);
    boolean deleteTaskById (int taskId);

}
