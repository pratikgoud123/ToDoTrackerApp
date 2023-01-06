/*
 *Author Name: Nikita Chauhan
 *Date: 06-01-2023
 *Created With: IntelliJ IDEA Community Edition
 */


package com.niit.UserAuthentication.config;

import com.niit.UserAuthentication.domain.User;
import com.niit.UserAuthentication.exception.UserAlreadyExistsException;
import com.niit.UserAuthentication.service.UserServiceImpl;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Consumer {

    @Autowired
    private UserServiceImpl userService;
    @RabbitListener(queues = "usertaskqueue")
    public void getData(UserDTO userDTO) throws UserAlreadyExistsException {
        User user=new User();
        user.setEmailId(userDTO.getEmailId());
        System.out.println("Mapping---"+userDTO.getEmailId());
        user.setPassword(userDTO.getPassword());
        System.out.println("user details--"+user);
        userService.addUser(user);
    }
}
