/*
 *Author Name: Nikita Chauhan
 *Date: 02-01-2023
 *Created With: IntelliJ IDEA Community Edition
 */


package com.niit.UserAuthentication.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT,reason = "User Already Exist !!!!!!")
public class UserAlreadyExistsException extends Exception{
}
