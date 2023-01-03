package com.niit.model;

import java.util.Date;

public class Task {

    private int taskId;
    private String taskName;
    private String taskContent;
    private boolean priority;
    private String taskCategory;
    private Date taskDueDate;

    public Task() {
    }

    public Task(int taskId, String taskName, String taskContent, boolean priority, String taskCategory, Date taskDueDate) {
        this.taskId = taskId;
        this.taskName = taskName;
        this.taskContent = taskContent;
        this.priority = priority;
        this.taskCategory = taskCategory;
        this.taskDueDate = taskDueDate;
    }

    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getTaskContent() {
        return taskContent;
    }

    public void setTaskContent(String taskContent) {
        this.taskContent = taskContent;
    }

    public boolean isPriority() {
        return priority;
    }

    public void setPriority(boolean priority) {
        this.priority = priority;
    }

    public String getTaskCategory() {
        return taskCategory;
    }

    public void setTaskCategory(String taskCategory) {
        this.taskCategory = taskCategory;
    }

    public Date getTaskDueDate() {
        return taskDueDate;
    }

    public void setTaskDueDate(Date taskDueDate) {
        this.taskDueDate = taskDueDate;
    }

    @Override
    public String toString() {
        return "Task{" +
                "taskId='" + taskId + '\'' +
                ", taskTitle='" + taskName + '\'' +
                ", taskDescription='" + taskContent + '\'' +
                ", isImportant=" + priority +
                ", category='" + taskCategory + '\'' +
                ", targetDate='" + taskDueDate + '\'' +
                '}';
    }
}
