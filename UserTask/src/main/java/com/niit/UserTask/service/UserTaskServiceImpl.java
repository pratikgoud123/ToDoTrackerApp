package com.niit.UserTask.service;

import com.niit.UserTask.config.Producer;
import com.niit.UserTask.config.UserDTO;
import com.niit.UserTask.domain.Task;
import com.niit.UserTask.domain.User;
import com.niit.UserTask.exception.TaskNotFoundException;
import com.niit.UserTask.exception.UserAlreadyExistsException;
import com.niit.UserTask.exception.UserNotFoundException;
import com.niit.UserTask.proxy.UserNotificationProxy;
import com.niit.UserTask.proxy.UserProxy;
import com.niit.UserTask.repository.UserTaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class UserTaskServiceImpl implements IUserTaskService{
    private UserTaskRepository userTaskRepository;
    private UserProxy userProxy;
    private UserNotificationProxy userNotificationProxy;
    @Autowired
    Producer producer;
    @Autowired
    public UserTaskServiceImpl(UserTaskRepository userTaskRepository, UserProxy userProxy, UserNotificationProxy userNotificationProxy) {
        this.userTaskRepository = userTaskRepository;
        this.userProxy = userProxy;
        this.userNotificationProxy = userNotificationProxy;
    }

    @Override
    public User saveUser(User user) throws UserAlreadyExistsException {
        if (userTaskRepository.findById(user.getUserId()).isPresent()){
            throw new UserAlreadyExistsException();
        }
        userProxy.saveUserDetailFromUserTask(user);
        userNotificationProxy.saveUserDetailFromUserTask(user);

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
    public Optional<User> getUserById(int userId) throws UserNotFoundException {
        if (userTaskRepository.findById(userId).isEmpty()){
            throw new UserNotFoundException();
        }
        User user1 = userTaskRepository.findById(userId).get();
        try{
            System.out.println(" user data fetched from client request---" + user1.toString());
            UserDTO userDTO = new UserDTO();

            userDTO.setUserId(user1.getUserId());
            userDTO.setFirstName(user1.getFirstName());
            userDTO.setLastName(user1.getLastName());
            userDTO.setEmailId(user1.getEmailId());
            userDTO.setPassword(user1.getPassword());
            userDTO.setRole(user1.getRole());
            userDTO.setTasks(user1.getTasks());

            producer.sendmsg(userDTO);

        }catch(Exception exception){
            System.out.println(exception.getStackTrace());
        }
        return userTaskRepository.findById(userId);
    }

    @Override
    public List<User> getUserByEmailId(String emailId) throws UserNotFoundException {
        if (userTaskRepository.findByEmailId(emailId).isEmpty()){
            throw new UserNotFoundException();
        }
        return userTaskRepository.findByEmailId(emailId);
    }

    @Override
    public User getByTaskId(int taskId) throws TaskNotFoundException {
        User userByTaskId = userTaskRepository.findByTaskId(taskId);
        if(userByTaskId == null){
           throw new TaskNotFoundException();
        }
        return userByTaskId;
    }

    @Override
    public boolean deleteAllUser() {
        userTaskRepository.deleteAll();
        return true;
    }

    @Override
    public boolean deleteUserById(int userId) throws UserNotFoundException{
        if (userTaskRepository.findById(userId).isEmpty()){
            throw new UserNotFoundException();
        }
        userTaskRepository.deleteById(userId);
        return true;
    }

    @Override
    public boolean deleteTaskById(int taskId) throws TaskNotFoundException {
        User userByTaskId = userTaskRepository.findByTaskId(taskId);
        if(userByTaskId == null){
            throw new TaskNotFoundException();
        }else{
            List<Task> tasks = userByTaskId.getTasks();
            for (Task taskToDelete: tasks) {
                taskToDelete.setTaskName(null);
                taskToDelete.setTaskContent(null);
                taskToDelete.setTaskDeadline(null);
                taskToDelete.setTaskPriorityLevel(null);
                taskToDelete.setTaskCompleted(false);
            }
            return true;
        }
    }
}
