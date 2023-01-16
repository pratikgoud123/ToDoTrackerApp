package com.niit.UserTask.service;

import com.niit.UserTask.config.Producer;
import com.niit.UserTask.config.UserDTO;
import com.niit.UserTask.domain.Task;
import com.niit.UserTask.domain.User;
import com.niit.UserTask.exception.TaskAlreadyExistsException;
import com.niit.UserTask.exception.TaskNotFoundException;
import com.niit.UserTask.exception.UserAlreadyExistsException;
import com.niit.UserTask.exception.UserNotFoundException;
import com.niit.UserTask.proxy.UserArchiveProxy;
import com.niit.UserTask.proxy.UserNotificationProxy;
import com.niit.UserTask.repository.UserTaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserTaskServiceImpl implements IUserTaskService{
    private final UserTaskRepository userTaskRepository;
    private UserNotificationProxy userNotificationProxy;
    private Producer producer;

    private UserArchiveProxy archiveProxy;
    @Autowired
    public UserTaskServiceImpl(UserTaskRepository userTaskRepository, UserNotificationProxy userNotificationProxy, Producer producer, UserArchiveProxy archiveProxy) {
        this.userTaskRepository = userTaskRepository;
        this.userNotificationProxy = userNotificationProxy;
        this.producer = producer;
        this.archiveProxy = archiveProxy;
    }

    @Override
    public User saveUser(User user, MultipartFile file) throws UserAlreadyExistsException, IOException {
        if (userTaskRepository.findById(user.getEmailId()).isPresent()){
            throw new UserAlreadyExistsException();
        }
        user.setImg(file.getBytes());
        user.setFile(file.getOriginalFilename());
        archiveProxy.saveUserToArchive(user);                                                                           //feignClient(Archive-service)
        userNotificationProxy.saveUserToNotification(user);                                                             //feignClient(Notification-service)

        try{
            System.out.println(" user data fetched from client request---" + user.toString());                          //RabbitMQ (UserAuthentication-service)
            UserDTO userDTO = new UserDTO();

            userDTO.setEmailId(user.getEmailId());
            userDTO.setPassword(user.getPassword());

            producer.sendUserMsg(userDTO);

        }catch(Exception exception){
            System.out.println(exception.getStackTrace());
        }
        return userTaskRepository.save(user);
    }

    @Override
    public Task addTask(String emailId, Task task) throws TaskAlreadyExistsException {
        User user1 = userTaskRepository.findById(emailId).get();
        List<Task> tasks = user1.getTasks();
        if(tasks == null){
            tasks = new ArrayList<>();
        }
        for (Task t : tasks) {
            if (t.getTaskName().equalsIgnoreCase(task.getTaskName())) {
                throw new TaskAlreadyExistsException();
            }
        }
        tasks.add(task);
        user1.setTasks(tasks);
        userTaskRepository.save(user1);
        userNotificationProxy.saveTaskDetailFromUserTask(emailId, task);                                                //feignClient(Notification-service)

        return task;
    }

    @Override
    public Task updateTask(String emailId, Task task ) {
        User user1 = userTaskRepository.findById(emailId).get();
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
        userTaskRepository.save(user1);
        userNotificationProxy.updateTask(emailId, task);                                                                //feignClient(Notification-service)

        return task;

    }

    @Override
    public List<User> getAllUsers() {
        return userTaskRepository.findAll();
    }

    @Override
    public List<Task> getAllTasksOfUser(String emailId) {
        User user1 = userTaskRepository.findById(emailId).get();
        List<Task> tasks = user1.getTasks();
        return tasks;
    }

    @Override
    public Optional<User> getUserById(String emailId) throws UserNotFoundException {
        if (userTaskRepository.findById(emailId).isEmpty()){
            throw new UserNotFoundException();
        }
        return userTaskRepository.findById(emailId);
    }

    @Override
    public Task getTaskByTaskId(String emailId, String taskName) throws TaskNotFoundException {
        User user1 = userTaskRepository.findById(emailId).get();
        List<Task> tasks = user1.getTasks();
        Task task = tasks.stream()
                            .filter(obj -> taskName.equalsIgnoreCase(obj.getTaskName()))
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
    public boolean deleteUserById(String emailId) throws UserNotFoundException{
        if (userTaskRepository.findById(emailId).isEmpty()){
            throw new UserNotFoundException();
        }
        userTaskRepository.deleteById(emailId);
        return true;
    }

    @Override
    public boolean deleteTaskByTaskId(String emailId, String taskName) throws TaskNotFoundException {
        User user = userTaskRepository.findById(emailId).get();
        List<Task> tasks = user.getTasks();
        Task task = tasks.stream()
                .filter(obj -> taskName.equalsIgnoreCase(obj.getTaskName()))
                .findAny().orElse(null);
        if(tasks == null || !tasks.contains(task)){
            throw new TaskNotFoundException();
        }
        tasks.remove(task);
        user.setTasks(tasks);
        userTaskRepository.save(user);

        return true;
    }

    @Override
    public List<Task> getCompletedTask(String emailId) {
        List<Task> tasks = userTaskRepository.findById(emailId).get().getTasks();
        List<Task> completed = new ArrayList<>();
        tasks.forEach(e->{if(e.isTaskCompleted())completed.add(e);});
        return completed;
    }
}
