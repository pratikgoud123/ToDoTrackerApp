package com.niit.service;

import com.niit.exception.ImpNotFoundException;
import com.niit.model.Task;
import com.niit.model.User;
import com.niit.repository.ImpRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ImpServiceImpl implements ImpService{



    @Autowired
    private ImpRepository impRepository;


    @Override
    public List<Task> getAllTask(int userId) {
        List<Task> tasks = impRepository.findById(userId).get().getTasks();

        return tasks;
    }

    @Override
    public boolean deleteTask(int userId, int taskId) throws ImpNotFoundException {
        User user = impRepository.findById(userId).get();
        List<Task> tasks = user.getTasks();
        Task task = tasks.stream()
                .filter(obj -> taskId==(obj.getTaskId()))
                .findAny().orElse(null);
        if(tasks == null || !tasks.contains(task)){
            throw new ImpNotFoundException();
        }
        tasks.remove(task);
        user.setTasks(tasks);
        impRepository.save(user);
        return true;
    }


    @Override
    public List<Task> getAllImpTask(int userId) {
        List<Task> tasks = impRepository.findById(userId).get().getTasks();
        List<Task> impTasks = new ArrayList<>();
        tasks.forEach(e->{
            if(e.getTaskPriorityLevel().equals("high")) {
                impTasks.add(e);
            } else if (e.getTaskPriorityLevel().equals("medium")) {
                impTasks.add(e);
            }
        });
        return impTasks;
    }

    @Override
    public boolean addTask(Task task, int userId) throws ImpNotFoundException {
        if(impRepository.findById(userId).isEmpty()){
            throw new ImpNotFoundException();
        }
        User user = impRepository.findById(userId).get();
        List<Task> tasks = user.getTasks();
        if(tasks == null) {
            tasks = new ArrayList<>();
        }
        tasks.add(task);
        user.setTasks(tasks);
        impRepository.save(user);
        return true;
    }
}
