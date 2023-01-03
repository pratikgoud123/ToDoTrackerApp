/*
 *Author Name: Nikita Chauhan
 *Date: 03-01-2023
 *Created With: IntelliJ IDEA Community Edition
 */


package com.niit.TaskArchive.domain;

import javax.annotation.Priority;
import java.time.LocalDate;

public class Task {

    private int taskId;
    private String taskName;
    private String taskContent;
    private LocalDate taskDueDate;
    private String taskCategory;
    private boolean priorityLevel;
    private boolean isCompleted;

    public Task() {
    }

    public Task(int taskId, String taskName, String taskContent, LocalDate taskDueDate, String taskCategory, boolean priorityLevel, boolean isCompleted) {
        this.taskId = taskId;
        this.taskName = taskName;
        this.taskContent = taskContent;
        this.taskDueDate = taskDueDate;
        this.taskCategory = taskCategory;
        this.priorityLevel = priorityLevel;
        this.isCompleted = isCompleted;
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

    public LocalDate getTaskDueDate() {
        return taskDueDate;
    }

    public void setTaskDueDate(LocalDate taskDueDate) {
        this.taskDueDate = taskDueDate;
    }

    public String getTaskCategory() {
        return taskCategory;
    }

    public void setTaskCategory(String taskCategory) {
        this.taskCategory = taskCategory;
    }

    public boolean isPriorityLevel() {
        return priorityLevel;
    }

    public void setPriorityLevel(boolean priorityLevel) {
        this.priorityLevel = priorityLevel;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }

    @Override
    public String toString() {
        return "Task{" + "taskId=" + taskId + ", taskName='" + taskName + '\'' + ", taskContent='" + taskContent + '\'' + ", taskDueDate=" + taskDueDate + ", taskCategory='" + taskCategory + '\'' + ", priorityLevel=" + priorityLevel + ", isCompleted=" + isCompleted + '}';
    }
}
