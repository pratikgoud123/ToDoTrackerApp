/*
 *Author Name: Nikita Chauhan
 *Date: 03-01-2023
 *Created With: IntelliJ IDEA Community Edition
 */


package com.niit.TaskArchive.service;

import com.niit.TaskArchive.domain.Task;
import com.niit.TaskArchive.domain.User;
import com.niit.TaskArchive.exception.TaskDoesNotExistsException;
import com.niit.TaskArchive.repository.ArchiveRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class ArchiveServiceImpl implements IArchiveService{

    private ArchiveRepository archiveRepository;

    @Autowired
    public ArchiveServiceImpl(ArchiveRepository archiveRepository) {
        this.archiveRepository = archiveRepository;
    }

    @Override
    public List<Task> getAllTasks(int userId) {

        List<Task> tasks = archiveRepository.findById(userId).get().getTasks();

        return tasks;
    }

    @Override
    public boolean deleteTask(int userId, int taskId) throws TaskDoesNotExistsException {
        User user = archiveRepository.findById(userId).get();
        List<Task> tasks = user.getTasks();
        Task task = tasks.stream()
                .filter(obj -> taskId==(obj.getTaskId()))
                .findAny().orElse(null);
        if(tasks == null || !tasks.contains(task)){
            throw new TaskDoesNotExistsException();
        }
        tasks.remove(task);
        user.setTasks(tasks);
        archiveRepository.save(user);
        return true;
    }

    @Override
    public User saveUser(User user) {

        return archiveRepository.save(user);
    }

    @Override
    public boolean addTask(Task task, int userId) {

        User user1 = archiveRepository.findById(userId).get();
        List<Task> tasks = user1.getTasks();

        if(user1.getTasks()==null){
            user1.setTasks(Arrays.asList(task));
        }
        else {
            tasks.add(task);
            user1.setTasks(tasks);
        }
        archiveRepository.save(user1);

        return true;
    }

    @Override
    public Task updateTask(int userId, Task task) {

        User user1 = archiveRepository.findById(userId).get();
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
        archiveRepository.save(user1);
        return task;
    }
}
