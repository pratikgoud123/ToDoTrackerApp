package com.niit.UserTask.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Arrays;
import java.util.List;

@Document
public class User {

    @Transient
    public static final String SEQUENCE_NAME = "user_sequence";
    @Id
    private int userId;
    private String firstName;
    private String lastName;
    private String file;
    private byte[] img;
    private String emailId;
    private String password;
    private String role;
    private List<Task> tasks;

    public User() {
    }

    public User(int userId, String firstName, String lastName, String file, byte[] img, String emailId, String password, String role, List<Task> tasks) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.file = file;
        this.img = img;
        this.emailId = emailId;
        this.password = password;
        this.role = role;
        this.tasks = tasks;
    }

    public byte[] getImg() {
        return img;
    }

    public void setImg(byte[] img) {
        this.img = img;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
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

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", file='" + file + '\'' +
                ", img=" + Arrays.toString(img) +
                ", emailId='" + emailId + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                ", tasks=" + tasks +
                '}';
    }
}
