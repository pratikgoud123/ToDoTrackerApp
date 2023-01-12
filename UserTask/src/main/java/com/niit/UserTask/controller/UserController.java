package com.niit.UserTask.controller;

import com.niit.UserTask.domain.Task;
import com.niit.UserTask.domain.User;
import com.niit.UserTask.exception.TaskNotFoundException;
import com.niit.UserTask.exception.UserAlreadyExistsException;
import com.niit.UserTask.exception.UserNotFoundException;
import com.niit.UserTask.service.UserTaskServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class UserController {
    private final UserTaskServiceImpl userTaskService;
    @Autowired
    public UserController(UserTaskServiceImpl userTaskService) {
        this.userTaskService = userTaskService;
    }

    @PostMapping("/AddUserInUserTask")
    public ResponseEntity<?> saveUser (@RequestBody User user) throws UserAlreadyExistsException {
        try{
            return new ResponseEntity<>(userTaskService.saveUser(user), HttpStatus.CREATED);
        }catch(UserAlreadyExistsException e){
            throw new UserAlreadyExistsException();
        }catch(Exception e){
            return new ResponseEntity<>("Server error, try after sometime", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/task/addTaskInUserTask/{userId}")
    public ResponseEntity<?> addTask (@PathVariable int userId, @RequestBody Task task) {
        return new ResponseEntity<>(userTaskService.addTask(userId, task), HttpStatus.CREATED);
    }

    @PutMapping("/task/updateTaskInUserTask/{userId}")
    public ResponseEntity<?> updateTask (@PathVariable int userId, @RequestBody Task task) {
        return new ResponseEntity<>(userTaskService.updateTask(task,userId ), HttpStatus.OK);
    }

    @GetMapping("/task/getAllUsersFromUserTask")
    public ResponseEntity<?> getAllUsers (){
        return new ResponseEntity<>(userTaskService.getAllUsers(), HttpStatus.OK);
    }

    @GetMapping("/task/getAllTasksOfUserFromUserTask/{userId}")
    public ResponseEntity<?> getAllTasksOfUser (@PathVariable int userId) {
        return new ResponseEntity<>(userTaskService.getAllTasksOfUser(userId), HttpStatus.OK);
    }

    @GetMapping("/task/getUserByIdInUserTask/{userId}")
    public ResponseEntity<?> getUserById (@PathVariable int userId) throws UserNotFoundException {
        try{
            return new ResponseEntity<>(userTaskService.getUserById(userId), HttpStatus.OK);
        }catch(UserNotFoundException e){
            throw new UserNotFoundException();
        }catch(Exception e){
            return new ResponseEntity<>("Server error, try after sometime", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/task/getUserByEmailIdInUserTask/{emailId}")
    public ResponseEntity<?> getUserByEmailId (@PathVariable String emailId) throws UserNotFoundException{
        try{
            return new ResponseEntity<>(userTaskService.getUserByEmailId(emailId), HttpStatus.OK);
        }catch(UserNotFoundException e){
            throw new UserNotFoundException();
        }catch(Exception e){
            return new ResponseEntity<>("Server error, try after sometime", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/task/getByTaskIdInUserTask/{userId}/{taskId}")
    public ResponseEntity<?> getTaskByTaskId (@PathVariable int userId, @PathVariable int taskId) throws TaskNotFoundException {
        try{
            return new ResponseEntity<>(userTaskService.getTaskByTaskId(userId, taskId), HttpStatus.OK);
        }catch(TaskNotFoundException e){
            throw new TaskNotFoundException();
        }catch(Exception e){
            return new ResponseEntity<>("Server error, try after sometime", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/task/deleteAllUserFromUserTask")
    public ResponseEntity<?> deleteAllUser () {
        return new ResponseEntity<>(userTaskService.deleteAllUser(), HttpStatus.OK);
    }

    @DeleteMapping("/task/deleteUserByIdInUserTask/{userId}")
    public ResponseEntity<?> deleteUserById (@PathVariable int userId) throws UserNotFoundException {
        try{
            return new ResponseEntity<>(userTaskService.deleteUserById(userId), HttpStatus.OK);
        }catch(UserNotFoundException e){
            throw new UserNotFoundException();
        }catch(Exception e){
            return new ResponseEntity<>("Server error, try after sometime", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/task/deleteTaskByTaskIdInUserTask/{userId}/{taskId}")
    public ResponseEntity<?> deleteTaskByTaskId (@PathVariable int userId, @PathVariable int taskId) throws TaskNotFoundException {
        try{
            return new ResponseEntity<>(userTaskService.deleteTaskByTaskId(userId, taskId), HttpStatus.OK);
        }catch(TaskNotFoundException e){
            throw new TaskNotFoundException();
        }catch(Exception e){
            return new ResponseEntity<>("Server error, try after sometime", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
