package com.niit.service;

import com.niit.exception.ImpNotFoundException;
import com.niit.model.Task;
import com.niit.model.User;

import java.util.List;

public interface ImpService {


    List<Task> getAllTask(int userId);

    User saveUser (User user);
    public boolean deleteTask(int userId, int taskId) throws ImpNotFoundException;

    public List<Task> getAllImpTask(int userId);
    public boolean addTask(Task task, int userId);
    Task updateTask (Task task,int userId );

    List<User> getAllUsers();

}
