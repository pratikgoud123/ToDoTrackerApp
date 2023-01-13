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

    @PostMapping("/addUserInArchive")
    public ResponseEntity<?> insertUser(@RequestBody User user){
        return new ResponseEntity<>(iArchiveService.saveUser(user),HttpStatus.OK);
    }
    @PutMapping("/addTaskInArchive/{userId}")
    public ResponseEntity<?> addTask(@RequestBody Task task, @PathVariable int userId){
        return new ResponseEntity<>(iArchiveService.addTask(task,userId),HttpStatus.OK);

    }
    @PutMapping("/updateTaskInArchive/{userId}")
    public ResponseEntity<?> updateTask (@PathVariable int userId, @RequestBody Task task) {
        return new ResponseEntity<>(iArchiveService.updateTask(userId, task), HttpStatus.OK);
    }

    @GetMapping("/getAllTasksFromArchive/{userId}")
    public ResponseEntity<?> getAllTasks(@PathVariable int userId){
        return new ResponseEntity<>(iArchiveService.getAllTasks(userId),HttpStatus.OK);
    }

    @DeleteMapping("/deleteTaskFromArchive/{userId}/{taskId}")
    public ResponseEntity<?> deleteTask(@PathVariable("userId") int userId, @PathVariable("taskId") int taskId) throws TaskDoesNotExistsException {
        iArchiveService.deleteTaskByTaskId(userId,taskId);
        return new ResponseEntity<>("Task deleted",HttpStatus.OK);

    }

    @GetMapping("/getByTaskIdInUserTask/{userId}/{taskId}")
    public ResponseEntity<?> getTaskByTaskId (@PathVariable int userId, @PathVariable int taskId) throws TaskDoesNotExistsException {
        try{
            return new ResponseEntity<>(iArchiveService.getTaskByTaskId(userId, taskId), HttpStatus.OK);
        }catch(TaskDoesNotExistsException e){
            throw new TaskDoesNotExistsException();
        }catch(Exception e){
            return new ResponseEntity<>("Server error, try after sometime", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
