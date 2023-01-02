package com.niit.UserTask.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code= HttpStatus.CONFLICT, reason="Registration failed! emailId is already registered")
public class UserAlreadyExistsException extends Exception{
}
