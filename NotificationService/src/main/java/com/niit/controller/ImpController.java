package com.niit.controller;


import com.niit.exception.ImpNotFoundException;

import com.niit.service.ImpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/api/imp")
public class ImpController {
    private ResponseEntity responseEntity;

    private ImpService impService;


    @Autowired
    public ImpController(ImpService impService) {
        this.impService = impService;
    }



    @GetMapping("/imptasks/{userId}")
    public ResponseEntity<?> getAllImpTasks(@PathVariable int userId){
        return new ResponseEntity<>(impService.getAllImpTask(userId),HttpStatus.OK);
    }


    @DeleteMapping("/delete/{userId}/{taskId}")
    public ResponseEntity<?> deleteTask(@PathVariable("userId") int userId, @PathVariable("taskId") int taskId) throws ImpNotFoundException {
        impService.deleteTask(userId,taskId);
        return new ResponseEntity<>("Task deleted",HttpStatus.OK);
    }
}
