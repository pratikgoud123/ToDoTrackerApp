package com.niit.UserTask.proxy;

import com.niit.UserTask.domain.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/*
to send data to Notification service
 */
@FeignClient(name="notification-service", url ="notification-service:8082")
public interface UserNotificationProxy {
    @PostMapping("/api/v4/registerUser")
    public ResponseEntity<?> saveUserDetailFromUserTask (@RequestBody User user);
}