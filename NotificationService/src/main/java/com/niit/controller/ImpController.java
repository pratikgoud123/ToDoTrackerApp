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
@RequestMapping("/api/v4")
public class ImpController {
    private ResponseEntity responseEntity;

    private ImpService impService;

    @Autowired
    public ImpController(ImpService impService) {
        this.impService = impService;
    }


    @PostMapping("/addUserInNotification")
    public ResponseEntity<?> saveUser(@RequestBody User user) {
        return new ResponseEntity<>(impService.saveUser(user), HttpStatus.OK);
    }

    @PutMapping("/addTaskInNotification/{emailId}")
    public ResponseEntity<?> addTask(@PathVariable String emailId, @RequestBody Task task) throws ImpAlreadyExistException {
        try {
            return new ResponseEntity<>(impService.addTask(emailId, task), HttpStatus.OK);
        } catch (ImpAlreadyExistException e) {
            throw new ImpAlreadyExistException();
        } catch (Exception e) {
            return new ResponseEntity<>("Server error, try after sometime", HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PutMapping("/updateTaskInNotification/{emailId}")
    public ResponseEntity<?> updateTask(@PathVariable String emailId, @RequestBody Task task) {
        return new ResponseEntity<>(impService.updateTask(emailId, task), HttpStatus.OK);
    }

    @GetMapping("/notification/impTasks/{emailId}")
    public ResponseEntity<?> getAllImpTasks(@PathVariable String emailId) {
        return new ResponseEntity<>(impService.getAllImpTask(emailId), HttpStatus.OK);
    }

    @GetMapping("/notification/getAllTasksFromNotification/{emailId}")
    public ResponseEntity<?> getAllTasks(@PathVariable String emailId) {
        return new ResponseEntity<>(impService.getAllTask(emailId), HttpStatus.OK);
    }


    @GetMapping("/notification/getAllUser")
    public ResponseEntity<?> getAllUser() {
        return new ResponseEntity<>(impService.getAllUsers(), HttpStatus.OK);
    }

    @DeleteMapping("/deleteFromNotification/{emailId}/{taskName}")
    public ResponseEntity<?> deleteTaskByTaskId(@PathVariable String emailId, @PathVariable String taskName) throws ImpNotFoundException {
        try {
            return new ResponseEntity<>(impService.deleteTaskByTaskId(emailId, taskName), HttpStatus.OK);
        } catch (ImpNotFoundException e) {
            throw new ImpNotFoundException();
        } catch (Exception e) {
            return new ResponseEntity<>("Server error, try after sometime", HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

}

