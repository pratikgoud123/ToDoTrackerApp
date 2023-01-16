package com.niit.service;

import com.niit.exception.ImpAlreadyExistException;
import com.niit.exception.ImpNotFoundException;
import com.niit.model.Task;
import com.niit.model.User;

import java.util.List;

public interface ImpService {


    List<Task> getAllTask(String emailId);
    User saveUser (User user) ;
    public boolean deleteTaskByTaskId(String emailId, String taskName) throws ImpNotFoundException;
    public List<Task> getAllImpTask(String emailId);
    public boolean addTask(String emailId, Task task) throws ImpAlreadyExistException;
    Task updateTask (String emailId, Task task);
    List<User> getAllUsers();

}
