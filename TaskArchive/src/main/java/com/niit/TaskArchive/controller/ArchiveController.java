/*
 *Author Name: Nikita Chauhan
 *Date: 03-01-2023
 *Created With: IntelliJ IDEA Community Edition
 */


package com.niit.TaskArchive.controller;

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

    @GetMapping("/fetchAllTasks")
    public ResponseEntity<?> fetchAllTasks() {
        return new ResponseEntity<>(iArchiveService.getAllTasks(), HttpStatus.OK);
    }


    @DeleteMapping("/deleteTaskById/{taskId}")
    public ResponseEntity<?> deleteTaskById(@PathVariable int taskId) throws TaskDoesNotExistsException {
        try {
            return new ResponseEntity<>(iArchiveService.deleteTaskById(taskId), HttpStatus.OK);
        } catch (TaskDoesNotExistsException e) {
            throw new TaskDoesNotExistsException();
        } catch (Exception e) {
            return new ResponseEntity<>("Server error, try after sometime", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
