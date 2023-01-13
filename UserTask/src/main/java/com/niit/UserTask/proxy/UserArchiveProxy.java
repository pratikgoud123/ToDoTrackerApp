package com.niit.UserTask.proxy;

import com.niit.UserTask.domain.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name="archive-service", url ="archive-service:8081")
public interface UserArchiveProxy {

    @PostMapping("/api/v3/addUserInArchive")
    public ResponseEntity<?> saveUserToarchive (@RequestBody User user);

}
