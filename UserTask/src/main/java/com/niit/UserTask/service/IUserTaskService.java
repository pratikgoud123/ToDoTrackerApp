package com.niit.UserTask.service;

import com.niit.UserTask.domain.Task;
import com.niit.UserTask.domain.User;
import com.niit.UserTask.exception.TaskAlreadyExistsException;
import com.niit.UserTask.exception.TaskNotFoundException;
import com.niit.UserTask.exception.UserAlreadyExistsException;
import com.niit.UserTask.exception.UserNotFoundException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface IUserTaskService {
    User saveUser (User user, MultipartFile file) throws UserAlreadyExistsException, IOException;
    Task addTask (String emailId, Task task) throws TaskAlreadyExistsException;
    Task updateTask (String emailId, Task task);
    List<User> getAllUsers ();
    List<Task> getAllTasksOfUser (String emailId);
    Optional<User> getUserById (String emailId) throws UserNotFoundException;
    Task getTaskByTaskId (String emailId, String taskName) throws TaskNotFoundException;
    boolean deleteAllUser ();
    boolean deleteUserById (String emailId) throws UserNotFoundException;
    boolean deleteTaskByTaskId (String emailId, String taskName) throws TaskNotFoundException;
    List<Task> getCompletedTask(String emailId);

}
