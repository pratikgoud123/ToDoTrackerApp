package com.niit.UserTask.service;

import com.niit.UserTask.domain.Task;
import com.niit.UserTask.domain.User;
import com.niit.UserTask.exception.TaskNotFoundException;
import com.niit.UserTask.exception.UserAlreadyExistsException;
import com.niit.UserTask.exception.UserNotFoundException;
import com.niit.UserTask.repository.UserTaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Service
public class UserTaskServiceImpl implements IUserTaskService{
    private UserTaskRepository userTaskRepository;
    @Autowired
    public UserTaskServiceImpl(UserTaskRepository userTaskRepository) {
        this.userTaskRepository = userTaskRepository;
    }

    @Override
    public User saveUser(User user) throws UserAlreadyExistsException {
        if (userTaskRepository.findById(user.getUserId()).isPresent()){
            throw new UserAlreadyExistsException();
        }
        return userTaskRepository.save(user);
    }

    @Override
    public User addTask(int userId, Task task) {
        User user1 = userTaskRepository.findById(userId).get();
        List<Task> tasks = user1.getTasks();
        tasks.add(task);
        return user1;
    }

    @Override
    public User updateTask(int userId, Task task) {
        User user1 = userTaskRepository.findById(userId).get();
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
        return user1;
    }

    @Override
    public List<User> getAllUsers() {
        return userTaskRepository.findAll();
    }

    @Override
    public List<Task> getAllTasksOfUser(int userId) {
        User user1 = userTaskRepository.findById(userId).get();
        List<Task> tasks = user1.getTasks();
        return tasks;
    }

    @Override
    public User getUserById(int userId) throws UserNotFoundException {
        if (userTaskRepository.findById(userId).isEmpty()){
            throw new UserNotFoundException();
        }

        return userTaskRepository.findById(userId).get();
    }

    @Override
    public Task getTaskById(int userId, int taskId) throws TaskNotFoundException {
        return null;
    }

    @Override
    public boolean deleteAllUser() {
        return false;
    }

    @Override
    public boolean deleteUserById(int userId) {
        return false;
    }

    @Override
    public boolean deleteTaskById(int taskId) {
        return false;
    }
}
