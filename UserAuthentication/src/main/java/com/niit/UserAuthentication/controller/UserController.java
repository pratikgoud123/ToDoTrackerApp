/*
 *Author Name: Nikita Chauhan
 *Date: 02-01-2023
 *Created With: IntelliJ IDEA Community Edition
 */


package com.niit.UserAuthentication.controller;

import com.niit.UserAuthentication.domain.LoginResponse;
import com.niit.UserAuthentication.domain.User;
import com.niit.UserAuthentication.exception.UserAlreadyExistsException;
import com.niit.UserAuthentication.exception.UserNotFoundException;
import com.niit.UserAuthentication.service.SecurityTokenGenerator;
import com.niit.UserAuthentication.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v2")
public class UserController {

    private final UserServiceImpl userService;

    private SecurityTokenGenerator securityTokenGenerator;

    @Autowired
    public UserController(UserServiceImpl userService, SecurityTokenGenerator securityTokenGenerator) {
        this.userService = userService;
        this.securityTokenGenerator = securityTokenGenerator;
    }

    @PostMapping("/AddUserInUserAuth")
    public ResponseEntity<?> insertUser(@RequestBody User user) throws UserAlreadyExistsException {
        try {
            return new ResponseEntity<>(userService.addUser(user), HttpStatus.CREATED);
        } catch (UserAlreadyExistsException e) {
            throw new UserAlreadyExistsException();
        } catch (Exception e) {
            return new ResponseEntity<>("Server error, try after sometime", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/user/fetchAllUsersFromUserAuth")
    public ResponseEntity<?> fetchAllUsers() {
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginFun(@RequestBody User user) throws UserNotFoundException {
        try {
            System.out.println("Before Mapping");
            User user1 = userService.loginCheck(user.getEmailId(), user.getPassword());
            System.out.println("After Mapping");
            Map<String, String> secretKey = new HashMap<>();
            secretKey = securityTokenGenerator.generateToken(user);
            LoginResponse loginResponse = new LoginResponse();
            loginResponse.setUser(user1);
            loginResponse.setSecretKeyToken(secretKey);
            return new ResponseEntity<>(loginResponse, HttpStatus.OK);
        } catch (UserNotFoundException e) {
            throw new UserNotFoundException();
        } catch (Exception e) {
            return new ResponseEntity<>("Network Error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/deleteByIdInUserAuth/{emailId}")
    public ResponseEntity<?> deleteByUserId(@PathVariable String emailId) {
        userService.deleteUserById(emailId);
        return new ResponseEntity<>("User record has been deleted", HttpStatus.OK);
    }
}
