package com.niit.UserTask.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskDTO {
    private int taskId;
    private String taskName;
    private String taskContent;
    private String imageURL;
    private LocalDate taskDeadline;
    private String taskCategory;
    private String taskPriorityLevel;
    private boolean isTaskCompleted;
}
