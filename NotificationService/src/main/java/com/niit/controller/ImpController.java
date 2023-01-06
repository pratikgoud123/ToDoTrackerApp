package com.niit.controller;


import com.niit.exception.ImpAlreadyExistException;
import com.niit.exception.ImpNotFoundException;

import com.niit.model.Task;
import com.niit.model.User;
import com.niit.service.ImpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/api/ex")
public class ImpController {
    private ResponseEntity responseEntity;

    private ImpService impService;
    @Autowired
    public ImpController(ImpService impService) {
        this.impService = impService;
    }


    @PostMapping("/addUserInNotification")
    public ResponseEntity<?> saveUser(@RequestBody User user){
        return new ResponseEntity<>(impService.saveUser(user),HttpStatus.OK);
    }
    @PutMapping("/addTaskInNotification/{userId}")
    public ResponseEntity<?> addTask(@RequestBody Task task, @PathVariable int userId){
        return new ResponseEntity<>(impService.addTask(task,userId),HttpStatus.OK);

    }
    @PutMapping("/updateTaskInNotification/{userId}")
    public ResponseEntity<?> updateTask (@PathVariable int userId, @RequestBody Task task) {
        return new ResponseEntity<>(impService.updateTask(userId, task), HttpStatus.OK);
    }
    @GetMapping("/imptasks/{userId}")
    public ResponseEntity<?> getAllImpTasks(@PathVariable int userId){
        return new ResponseEntity<>(impService.getAllImpTask(userId),HttpStatus.OK);
    }

    @GetMapping("/getAllTasksFromNotification/{userId}")
    public ResponseEntity<?> getAllTasks(@PathVariable int userId){
        return new ResponseEntity<>(impService.getAllTask(userId),HttpStatus.OK);
    }

    @DeleteMapping("/deleteFromNotification/{userId}/{taskId}")
    public ResponseEntity<?> deleteTask(@PathVariable("userId") int userId, @PathVariable("taskId") int taskId) throws ImpNotFoundException {
        impService.deleteTask(userId,taskId);
        return new ResponseEntity<>("Task deleted",HttpStatus.OK);

    }

}

