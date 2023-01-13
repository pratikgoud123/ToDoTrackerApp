package com.niit.UserTask.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import javax.annotation.processing.Generated;
import java.time.LocalDate;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Task {
    @Id
    private String taskName;
    private String taskContent;
    private String imageURL;
    private LocalDate taskDeadline;
    private String taskCategory;
    private String taskPriorityLevel;
    private boolean isTaskCompleted;

}
