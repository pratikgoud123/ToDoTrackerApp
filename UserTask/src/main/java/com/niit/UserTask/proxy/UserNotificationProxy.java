package com.niit.UserTask.proxy;

import com.niit.UserTask.domain.Task;

import com.niit.UserTask.domain.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

/*
to send data to Notification service
 */
@FeignClient(name="notification-service", url ="notification-service:8082")
public interface UserNotificationProxy {


    @PostMapping("/api/v4/addUserInNotification")
    public ResponseEntity<?> saveUserToNotification (@RequestBody User user);
    @PutMapping("/api/v4/addTaskInNotification/{emailId}")
    public ResponseEntity<?> saveTaskDetailFromUserTask (@PathVariable String emailId, @RequestBody Task task);
    @PutMapping("/api/v4/updateTaskInNotification/{emailId}")
    public ResponseEntity<?> updateTask (@PathVariable String emailId, @RequestBody Task task) ;
}
