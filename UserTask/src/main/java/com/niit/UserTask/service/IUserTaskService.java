package com.niit.UserTask.service;

import com.niit.UserTask.domain.Task;
import com.niit.UserTask.domain.User;
import com.niit.UserTask.exception.TaskNotFoundException;
import com.niit.UserTask.exception.UserAlreadyExistsException;
import com.niit.UserTask.exception.UserNotFoundException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface IUserTaskService {
    User saveUser (User user, MultipartFile file) throws UserAlreadyExistsException, IOException;
    Task addTask (int userId, Task task);
    Task updateTask (Task task,int userId);
    List<User> getAllUsers ();
    List<Task> getAllTasksOfUser (int userId);
    Optional<User> getUserById (int userId) throws UserNotFoundException;
    List<User> getUserByEmailId (String emailId) throws UserNotFoundException;
    Task getTaskByTaskId (int userId, int taskId) throws TaskNotFoundException;
    boolean deleteAllUser ();
    boolean deleteUserById (int userId) throws UserNotFoundException;
    boolean deleteTaskByTaskId (int userId, int taskId) throws TaskNotFoundException;

}
