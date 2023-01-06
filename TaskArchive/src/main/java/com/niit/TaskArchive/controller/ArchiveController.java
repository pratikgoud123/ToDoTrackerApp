/*
 *Author Name: Nikita Chauhan
 *Date: 03-01-2023
 *Created With: IntelliJ IDEA Community Edition
 */


package com.niit.TaskArchive.controller;

import com.niit.TaskArchive.domain.Task;
import com.niit.TaskArchive.domain.User;
import com.niit.TaskArchive.exception.TaskDoesNotExistsException;
import com.niit.TaskArchive.service.IArchiveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v3")
public class ArchiveController {

    private final IArchiveService iArchiveService;

    @Autowired
    public ArchiveController(IArchiveService iArchiveService) {
        this.iArchiveService = iArchiveService;
    }

    @PostMapping("/insertUser")
    public ResponseEntity<?> insertUser(@RequestBody User user){
        return new ResponseEntity<>(iArchiveService.saveUser(user),HttpStatus.OK);
    }
    @PutMapping("/add/{userId}")
    public ResponseEntity<?> addTask(@RequestBody Task task, @PathVariable int userId){
        return new ResponseEntity<>(iArchiveService.addTask(task,userId),HttpStatus.OK);

    }
    @PutMapping("/updateTask/{userId}")
    public ResponseEntity<?> updateTask (@PathVariable int userId, @RequestBody Task task) {
        return new ResponseEntity<>(iArchiveService.updateTask(userId, task), HttpStatus.OK);
    }

    @GetMapping("/getAllTasks/{userId}")
    public ResponseEntity<?> getAllTasks(@PathVariable int userId){
        return new ResponseEntity<>(iArchiveService.getAllTasks(userId),HttpStatus.OK);
    }


    @DeleteMapping("/deleteTask/{userId}/{taskId}")
    public ResponseEntity<?> deleteTask(@PathVariable("userId") int userId, @PathVariable("taskId") int taskId) throws TaskDoesNotExistsException {
        iArchiveService.deleteTask(userId,taskId);
        return new ResponseEntity<>("Task deleted",HttpStatus.OK);

    }
}
