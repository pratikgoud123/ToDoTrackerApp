package com.niit.service;

import com.niit.exception.ImpNotFoundException;
import com.niit.model.Task;

import java.util.List;

public interface ImpService {


    List<Task> getAllTask(int userId);

    public boolean deleteTask(int userId, int taskId) throws ImpNotFoundException;

    public List<Task> getAllImpTask(int userId);
    public boolean addTask(Task task, int userId);
}
