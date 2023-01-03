/*
 *Author Name: Nikita Chauhan
 *Date: 03-01-2023
 *Created With: IntelliJ IDEA Community Edition
 */


package com.niit.TaskArchive.service;

import com.niit.TaskArchive.domain.Task;
import com.niit.TaskArchive.exception.TaskDoesNotExistsException;
import com.niit.TaskArchive.repository.ArchiveRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArchiveServiceImpl implements IArchiveService{

    private ArchiveRepository archiveRepository;

    @Autowired
    public ArchiveServiceImpl(ArchiveRepository archiveRepository) {
        this.archiveRepository = archiveRepository;
    }

    @Override
    public List<Task> getAllTasks() {
        return archiveRepository.findAll();
    }

    @Override
    public boolean deleteTaskById(int taskId) throws TaskDoesNotExistsException {
        if (archiveRepository.findById(taskId).isEmpty()){
            throw new TaskDoesNotExistsException();
        }
        archiveRepository.deleteById(taskId);
        return true;
    }
}
