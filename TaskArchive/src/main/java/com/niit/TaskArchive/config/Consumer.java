/*
 *Author Name: Nikita Chauhan
 *Date: 03-01-2023
 *Created With: IntelliJ IDEA Community Edition
 */


package com.niit.TaskArchive.config;

import com.niit.TaskArchive.domain.User;
import com.niit.TaskArchive.service.ArchiveServiceImpl;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;

public class Consumer {

    @Autowired
    private ArchiveServiceImpl archiveService;
    @RabbitListener(queues = "usertaskqueue")
    public void getData(UserDTO userDTO){
        User user=new User();
        user.setUserId(userDTO.getUserId());
        user.setFirstName(userDTO.getFirstName());

        user.setLastName(userDTO.getLastName());
        user.setEmailId(userDTO.getEmailId());
        user.setPassword(userDTO.getPassword());
        user.setRole(userDTO.getRole());
        user.setTasks(userDTO.getTasks());
        System.out.println("Mapping---"+userDTO.getTasks());
        System.out.println("user details--"+user);
        archiveService.getAllTasks();
    }
}
