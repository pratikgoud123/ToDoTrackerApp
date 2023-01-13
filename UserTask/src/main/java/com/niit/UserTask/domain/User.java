package com.niit.UserTask.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Arrays;
import java.util.List;

@Data
@Document
@NoArgsConstructor
@AllArgsConstructor
public class User {

    private String firstName;
    private String lastName;
    private String file;
    private byte[] img;
    private String imageURL;
    @Id
    private String emailId;
    private String password;
    private String role;
    private List<Task> tasks;


}
