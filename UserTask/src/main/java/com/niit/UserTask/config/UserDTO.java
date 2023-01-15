package com.niit.UserTask.config;

import com.niit.UserTask.domain.Task;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private String firstName;
    private String lastName;
    private String file;
    private byte[] img;
    private String imageURL;
    private String emailId;
    private String password;
    private String role;
    private List<Task> tasks;
}
