package com.cydeo.service;

import com.cydeo.dto.TaskDTO;
import com.cydeo.entity.Task;
import com.cydeo.enums.Status;

import java.util.List;

public interface TaskService {
    void save(TaskDTO taskDTO);
    void delete(Long id);
    void update(TaskDTO taskDTO);
    List<TaskDTO>listAllTasks();
    TaskDTO findById(Long id);
   TaskDTO findAllTasksByStatusIsNot(Status status);
   List<TaskDTO>findAllTasksByStatus(Status status);
int totalNonCompletedTask(String projectCode);
int totalCompletedTask(String projectCode);


}
