package com.niit.UserTask.service;

import com.niit.UserTask.config.Producer;
import com.niit.UserTask.config.TaskDTO;
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

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
@Service
public class UserTaskServiceImpl implements IUserTaskService{
    private UserTaskRepository userTaskRepository;
    private UserProxy userProxy;
    private UserNotificationProxy userNotificationProxy;
    private Producer producer;
    @Autowired
    public UserTaskServiceImpl(UserTaskRepository userTaskRepository, UserProxy userProxy, UserNotificationProxy userNotificationProxy, Producer producer) {
        this.userTaskRepository = userTaskRepository;
        this.userProxy = userProxy;
        this.userNotificationProxy = userNotificationProxy;
        this.producer = producer;
    }

    @Override
    public User saveUser(User user) throws UserAlreadyExistsException {
        if (userTaskRepository.findById(user.getUserId()).isPresent()){
            throw new UserAlreadyExistsException();
        }
        userProxy.saveUserDetailFromUserTask(user);

        return userTaskRepository.save(user);
    }

    @Override
    public Task addTask(int userId, Task task) {
        User user1 = userTaskRepository.findById(userId).get();
        List<Task> tasks = user1.getTasks();
        if(tasks == null){
            user1.setTasks(Arrays.asList(task));
        }else {
            tasks.add(task);
            user1.setTasks(tasks);
        }

        userNotificationProxy.saveTaskDetailFromUserTask(task, userId);                                                 //feignClient(Notification-service)

        try{
            System.out.println(" task data fetched from client request---" + task.toString());                          //RabbitMQ (TaskArchive-service)
            TaskDTO taskDTO = new TaskDTO();

            taskDTO.setTaskId(task.getTaskId());
            taskDTO.setTaskName(task.getTaskName());
            taskDTO.setTaskContent(task.getTaskContent());
            taskDTO.setTaskDeadline(task.getTaskDeadline());
            taskDTO.setTaskCategory(task.getTaskCategory());
            taskDTO.setTaskPriorityLevel(task.getTaskPriorityLevel());
            taskDTO.setTaskCompleted(task.isTaskCompleted());
            producer.sendTaskMsg(taskDTO);

        }catch(Exception exception){
            System.out.println(exception.getStackTrace());
        }

        return task;
    }

    @Override
    public Task updateTask(int userId, Task task) {
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

        try{
            System.out.println(" task data fetched from client request---" + task.toString());                          //RabbitMQ (TaskArchive-service)
            TaskDTO taskDTO = new TaskDTO();

            taskDTO.setTaskId(task.getTaskId());
            taskDTO.setTaskName(task.getTaskName());
            taskDTO.setTaskContent(task.getTaskContent());
            taskDTO.setTaskDeadline(task.getTaskDeadline());
            taskDTO.setTaskCategory(task.getTaskCategory());
            taskDTO.setTaskPriorityLevel(task.getTaskPriorityLevel());
            taskDTO.setTaskCompleted(task.isTaskCompleted());
            producer.sendTaskMsg(taskDTO);

        }catch(Exception exception){
            System.out.println(exception.getStackTrace());
        }

        return task;
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

            producer.sendUserMsg(userDTO);

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
    public Task getTaskByTaskId(int userId, int taskId) throws TaskNotFoundException {
        User user1 = userTaskRepository.findById(userId).get();
        List<Task> tasks = user1.getTasks();
        Task task = tasks.stream()
                            .filter(obj -> taskId==(obj.getTaskId()))
                            .findAny().orElse(null);
        if(tasks == null || !tasks.contains(task)){
            throw new TaskNotFoundException();
        }
        userTaskRepository.save(user1);

        return task;
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
    public boolean deleteTaskByTaskId(int userId, int taskId) throws TaskNotFoundException {
        User user = userTaskRepository.findById(userId).get();
        List<Task> tasks = user.getTasks();
        Task task = tasks.stream()
                .filter(obj -> taskId==(obj.getTaskId()))
                .findAny().orElse(null);
        if(tasks == null || !tasks.contains(task)){
            throw new TaskNotFoundException();
        }
        tasks.remove(task);
        user.setTasks(tasks);
        userTaskRepository.save(user);
        return true;
    }
}
