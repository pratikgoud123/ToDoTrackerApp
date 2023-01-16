package com.niit.UserTask.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.UniqueElements;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.annotation.processing.Generated;
import java.time.LocalDate;
import java.util.Date;

@Data
@Document
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
