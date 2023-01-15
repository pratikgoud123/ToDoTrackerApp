package com.niit.TaskArchive.service;

import com.niit.TaskArchive.domain.Task;
import com.niit.TaskArchive.domain.User;
import com.niit.TaskArchive.exception.TaskDoesNotExistsException;

import java.util.List;

public interface IArchiveService {

    List<Task> getAllTasks(String emailId);
    boolean deleteTaskByTaskId(String emailId, String taskName) throws TaskDoesNotExistsException;
    User saveUser (User user);
    boolean addTask(String emailId, Task task);
    Task updateTask (String emailId, Task task);
    Task getTaskByTaskId (String emailId, String taskName) throws TaskDoesNotExistsException;

}
