package com.niit.UserTask.config;

import java.time.LocalDate;
import java.util.Date;

public class TaskDTO {
    private  int taskId;
    private String taskName;
    private String taskContent;
    private LocalDate taskDeadline;
    private String taskCategory;
    private String taskPriorityLevel;
    private boolean isTaskCompleted;

    public TaskDTO() {
    }

    public TaskDTO(int taskId, String taskName, String taskContent, LocalDate taskDeadline, String taskCategory, String taskPriorityLevel, boolean isTaskCompleted) {
        this.taskId = taskId;
        this.taskName = taskName;
        this.taskContent = taskContent;
        this.taskDeadline = taskDeadline;
        this.taskCategory = taskCategory;
        this.taskPriorityLevel = taskPriorityLevel;
        this.isTaskCompleted = isTaskCompleted;
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

    public LocalDate getTaskDeadline() {
        return taskDeadline;
    }

    public void setTaskDeadline(LocalDate taskDeadline) {
        this.taskDeadline = taskDeadline;
    }

    public String getTaskCategory() {
        return taskCategory;
    }

    public void setTaskCategory(String taskCategory) {
        this.taskCategory = taskCategory;
    }

    public String getTaskPriorityLevel() {
        return taskPriorityLevel;
    }

    public void setTaskPriorityLevel(String taskPriorityLevel) {
        this.taskPriorityLevel = taskPriorityLevel;
    }

    public boolean isTaskCompleted() {
        return isTaskCompleted;
    }

    public void setTaskCompleted(boolean taskCompleted) {
        isTaskCompleted = taskCompleted;
    }

    @Override
    public String toString() {
        return "TaskDTO{" +
                "taskId=" + taskId +
                ", taskName='" + taskName + '\'' +
                ", taskContent='" + taskContent + '\'' +
                ", taskDeadline=" + taskDeadline +
                ", taskCategory='" + taskCategory + '\'' +
                ", taskPriorityLevel='" + taskPriorityLevel + '\'' +
                ", isTaskCompleted=" + isTaskCompleted +
                '}';
    }
}
