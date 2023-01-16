package com.niit.UserTask.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.niit.UserTask.domain.Task;
import com.niit.UserTask.domain.User;
import com.niit.UserTask.exception.TaskAlreadyExistsException;
import com.niit.UserTask.exception.TaskNotFoundException;
import com.niit.UserTask.exception.UserAlreadyExistsException;
import com.niit.UserTask.exception.UserNotFoundException;
import com.niit.UserTask.service.UserTaskServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1")
public class UserController {
    private final UserTaskServiceImpl userTaskService;
    @Autowired
    public UserController(UserTaskServiceImpl userTaskService) {
        this.userTaskService = userTaskService;
    }

    @PostMapping("/AddUserInUserTask")
    public ResponseEntity<?> saveUser (@RequestParam("file") MultipartFile file, @RequestParam("user") String user) throws UserAlreadyExistsException, IOException {
        User user1 = new ObjectMapper().readValue(user, User.class);
        try{
            return new ResponseEntity<>(userTaskService.saveUser(user1,file), HttpStatus.CREATED);
        }catch(UserAlreadyExistsException e){
            throw new UserAlreadyExistsException();
        }catch(Exception e){
            return new ResponseEntity<>("Server error, try after sometime", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/task/addTaskInUserTask/{emailId}")
    public ResponseEntity<?> addTask (@PathVariable String emailId, @RequestBody Task task) throws TaskAlreadyExistsException {
        try{
            return new ResponseEntity<>(userTaskService.addTask(emailId, task), HttpStatus.CREATED);
        }catch(TaskAlreadyExistsException e){
            throw new TaskAlreadyExistsException();
        }catch(Exception e){
            return new ResponseEntity<>("Server error, try after sometime", HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PutMapping("/task/updateTaskInUserTask/{emailId}")
    public ResponseEntity<?> updateTask (@PathVariable String emailId, @RequestBody Task task) {
        return new ResponseEntity<>(userTaskService.updateTask(emailId, task), HttpStatus.OK);
    }

    @GetMapping("/task/getAllUsersFromUserTask")
    public ResponseEntity<?> getAllUsers (){
        return new ResponseEntity<>(userTaskService.getAllUsers(), HttpStatus.OK);
    }

    @GetMapping("/task/getAllTasksOfUserFromUserTask/{emailId}")
    public ResponseEntity<?> getAllTasksOfUser (@PathVariable String emailId) {
        return new ResponseEntity<>(userTaskService.getAllTasksOfUser(emailId), HttpStatus.OK);
    }

    @GetMapping("/task/getUserByIdInUserTask/{emailId}")
    public ResponseEntity<?> getUserById (@PathVariable String emailId) throws UserNotFoundException {
        try{
            return new ResponseEntity<>(userTaskService.getUserById(emailId), HttpStatus.OK);
        }catch(UserNotFoundException e){
            throw new UserNotFoundException();
        }catch(Exception e){
            return new ResponseEntity<>("Server error, try after sometime", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/task/getByTaskIdInUserTask/{emailId}/{taskName}")
    public ResponseEntity<?> getTaskByTaskId (@PathVariable String emailId, @PathVariable String taskName) throws TaskNotFoundException {
        try{
            return new ResponseEntity<>(userTaskService.getTaskByTaskId(emailId, taskName), HttpStatus.OK);
        }catch(TaskNotFoundException e){
            throw new TaskNotFoundException();
        }catch(Exception e){
            return new ResponseEntity<>("Server error, try after sometime", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/completed/{emailId}")
    public ResponseEntity<?> getAllImpTasks(@PathVariable String emailId){
        return new ResponseEntity<>(userTaskService.getCompletedTask(emailId),HttpStatus.OK);
    }

    @DeleteMapping("/task/deleteAllUserFromUserTask")
    public ResponseEntity<?> deleteAllUser () {
        return new ResponseEntity<>(userTaskService.deleteAllUser(), HttpStatus.OK);
    }

    @DeleteMapping("/task/deleteUserByIdInUserTask/{emailId}")
    public ResponseEntity<?> deleteUserById (@PathVariable String emailId) throws UserNotFoundException {
        try{
            return new ResponseEntity<>(userTaskService.deleteUserById(emailId), HttpStatus.OK);
        }catch(UserNotFoundException e){
            throw new UserNotFoundException();
        }catch(Exception e){
            return new ResponseEntity<>("Server error, try after sometime", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/task/deleteTaskByTaskIdInUserTask/{emailId}/{taskName}")
    public ResponseEntity<?> deleteTaskByTaskId (@PathVariable String emailId, @PathVariable String taskName) throws TaskNotFoundException {
        try{
            return new ResponseEntity<>(userTaskService.deleteTaskByTaskId(emailId, taskName), HttpStatus.OK);
        }catch(TaskNotFoundException e){
            throw new TaskNotFoundException();
        }catch(Exception e){
            return new ResponseEntity<>("Server error, try after sometime", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
