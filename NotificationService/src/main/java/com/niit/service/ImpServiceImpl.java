package com.niit.service;

import com.niit.exception.ImpAlreadyExistException;
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
    public List<Task> getAllTask(String emailId) {
        List<Task> tasks = impRepository.findById(emailId).get().getTasks();
        return tasks;
    }

    @Override
    public User saveUser(User user) {
        return impRepository.save(user);
    }

    @Override
    public boolean deleteTaskByTaskId(String emailId, String taskName) throws ImpNotFoundException {
        User user = impRepository.findById(emailId).get();
        List<Task> tasks = user.getTasks();
        Task task = tasks.stream()
                .filter(obj -> taskName==(obj.getTaskName()))
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
    public List<Task> getAllImpTask(String emailId) {
        List<Task> tasks = impRepository.findById(emailId).get().getTasks();
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
    public boolean addTask(String emailId, Task task) throws ImpAlreadyExistException {
        User user1 = impRepository.findById(emailId).get();
        List<Task> tasks = user1.getTasks();
        if(tasks == null){
            tasks = new ArrayList<>();
        }
        for (Task t : tasks) {
            if (t.getTaskName().equalsIgnoreCase(task.getTaskName())) {
                throw new ImpAlreadyExistException();
            }
        }
        tasks.add(task);
        user1.setTasks(tasks);

        impRepository.save(user1);
        return true;
    }

    @Override
    public Task updateTask(String emailId, Task task) {
        User user1 = impRepository.findById(emailId).get();
        List<Task> tasks = user1.getTasks();
        for (Task taskToUpdate: tasks) {
            if (taskToUpdate.getTaskName().equalsIgnoreCase(task.getTaskName())){
                taskToUpdate.setTaskName(task.getTaskName());
                taskToUpdate.setTaskContent(task.getTaskContent());
                taskToUpdate.setImageURL(task.getImageURL());
                taskToUpdate.setTaskDeadline(task.getTaskDeadline());
                taskToUpdate.setTaskCategory(task.getTaskCategory());
                taskToUpdate.setTaskPriorityLevel(task.getTaskPriorityLevel());
                taskToUpdate.setTaskCompleted(task.isTaskCompleted());
            }
        }
        impRepository.save(user1);
        return task;
    }

    @Override
    public List<User> getAllUsers() {
        return impRepository.findAll();
    }


}
