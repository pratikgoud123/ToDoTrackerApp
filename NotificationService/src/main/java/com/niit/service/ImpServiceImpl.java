package com.niit.service;

import com.niit.exception.ImpNotFoundException;
import com.niit.model.Task;
import com.niit.model.User;
import com.niit.repository.ImpRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
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
    public User saveUser(User user) {
        return impRepository.save(user);
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
    public boolean addTask(Task task, int userId) {
        User user1 = impRepository.findById(userId).get();
        List<Task> tasks = user1.getTasks();

        if(user1.getTasks()==null){
            user1.setTasks(Arrays.asList(task));
        }
        else {
            tasks.add(task);
            user1.setTasks(tasks);
        }
        impRepository.save(user1);

        return true;
    }

    @Override
    public Task updateTask(int userId, Task task) {
        User user1 = impRepository.findById(userId).get();
        List<Task> tasks = user1.getTasks();
        for (Task taskToUpdate: tasks) {
            if (taskToUpdate.getTaskId() == task.getTaskId()){
                taskToUpdate.setTaskName(task.getTaskName());
                taskToUpdate.setTaskContent(task.getTaskContent());
                taskToUpdate.setTaskDeadline(task.getTaskDeadline());
                taskToUpdate.setTaskPriorityLevel(task.getTaskPriorityLevel());
                taskToUpdate.setTaskCompleted(task.isTaskCompleted());
            }
        }
        impRepository.save(user1);
        return task;
    }


}
