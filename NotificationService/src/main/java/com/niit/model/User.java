package com.niit.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document
public class User {

    @Id
    private int userId;
    private String emailId;
    private String firstName;
    private String lastName;
    private String role;
    private List<Task> tasks;

    public User() {
    }


    public User(int userId, String emailId, String firstName, String lastName, String role, List<Task> tasks) {
        this.userId = userId;
        this.emailId = emailId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.role = role;
        this.tasks = tasks;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", emailId='" + emailId + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", role='" + role + '\'' +
                ", tasks=" + tasks +
                '}';
    }
}
