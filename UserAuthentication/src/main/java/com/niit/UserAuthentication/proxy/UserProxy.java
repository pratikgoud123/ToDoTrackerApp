package com.niit.UserAuthentication.proxy;

import com.niit.UserAuthentication.domain.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

//@FeignClient(name = "tracker-service", url = "tracker-service:8083")  // name of other project
//public interface UserProxy {
   // @PostMapping("api/v2/registerUser")// from user authentication controller
   // public ResponseEntity<?> register(@RequestBody User user);
//}
