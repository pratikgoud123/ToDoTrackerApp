package com.niit.TaskArchive.service;

import com.niit.TaskArchive.domain.Task;
import com.niit.TaskArchive.exception.TaskDoesNotExistsException;

import java.util.List;

public interface IArchiveService {

    List<Task> getAllTasks();

    boolean deleteTaskById (int taskId) throws TaskDoesNotExistsException;

}
