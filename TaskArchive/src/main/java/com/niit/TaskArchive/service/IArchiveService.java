package com.niit.TaskArchive.service;

import com.niit.TaskArchive.domain.Task;
import com.niit.TaskArchive.domain.User;
import com.niit.TaskArchive.exception.TaskDoesNotExistsException;

import java.util.List;

public interface IArchiveService {

    List<Task> getAllTasks(int userId);
    boolean deleteTaskByTaskId(int userId, int taskId) throws TaskDoesNotExistsException;
    User saveUser (User user);
    public boolean addTask(Task task, int userId);
    Task updateTask (int userId, Task task);
    Task getTaskByTaskId (int userId, int taskId) throws TaskDoesNotExistsException;

}
