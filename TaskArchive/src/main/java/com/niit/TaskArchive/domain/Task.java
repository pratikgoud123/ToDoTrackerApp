/*
 *Author Name: Nikita Chauhan
 *Date: 03-01-2023
 *Created With: IntelliJ IDEA Community Edition
 */


package com.niit.TaskArchive.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
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
