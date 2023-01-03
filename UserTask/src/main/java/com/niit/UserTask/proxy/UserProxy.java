package com.niit.UserTask.proxy;

import com.niit.UserTask.domain.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name="authentication-service", url ="authentication-service:8083")
public interface UserProxy {
    @PostMapping("/api/v1/registerUser")
    public ResponseEntity<?> saveUserDetailFromUserTask (@RequestBody User user);

}