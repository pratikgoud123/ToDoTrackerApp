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
    @PutMapping("/addTaskInArchive/{emailId}")
    public ResponseEntity<?> addTask(@PathVariable String emailId, @RequestBody Task task){
        return new ResponseEntity<>(iArchiveService.addTask(emailId, task),HttpStatus.OK);

    }
    @PutMapping("/updateTaskInArchive/{emailId}")
    public ResponseEntity<?> updateTask (@PathVariable String emailId, @RequestBody Task task) {
        return new ResponseEntity<>(iArchiveService.updateTask(emailId, task), HttpStatus.OK);
    }

    @GetMapping("/getAllTasksFromArchive/{emailId}")
    public ResponseEntity<?> getAllTasks(@PathVariable String emailId){
        return new ResponseEntity<>(iArchiveService.getAllTasks(emailId),HttpStatus.OK);
    }

    @DeleteMapping("/deleteTaskFromArchive/{emailId}/{taskName}")
    public ResponseEntity<?> deleteTask(@PathVariable String emailId, @PathVariable String taskName) throws TaskDoesNotExistsException {
        iArchiveService.deleteTaskByTaskId(emailId,taskName);
        return new ResponseEntity<>("Task deleted",HttpStatus.OK);

    }

    @GetMapping("/getByTaskIdFromArchive/{emailId}/{taskName}")
    public ResponseEntity<?> getTaskByTaskId (@PathVariable String emailId, @PathVariable String taskName) throws TaskDoesNotExistsException {
        try{
            return new ResponseEntity<>(iArchiveService.getTaskByTaskId(emailId, taskName), HttpStatus.OK);
        }catch(TaskDoesNotExistsException e){
            throw new TaskDoesNotExistsException();
        }catch(Exception e){
            return new ResponseEntity<>("Server error, try after sometime", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
