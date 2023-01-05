package com.niit.UserTask.proxy;

import com.niit.UserTask.domain.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/*
To send data to userAuthentication Service
 */
@FeignClient(name="authentication-service", url ="authentication-service:8086")
public interface UserProxy {
    @PostMapping("/api/v2/registerUser")
    public ResponseEntity<?> saveUserDetailFromUserTask (@RequestBody User user);

}
