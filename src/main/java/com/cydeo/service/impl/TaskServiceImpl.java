package com.cydeo.service.impl;

import com.cydeo.dto.TaskDTO;
import com.cydeo.entity.Task;
import com.cydeo.enums.Status;
import com.cydeo.mapper.TaskMapper;
import com.cydeo.repository.TaskRepository;
import com.cydeo.service.TaskService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TaskServiceImpl implements TaskService {

    @Override
    public TaskDTO findAllTasksByStatusIsNot(Status status) {
   Task task=   taskRepository.findByTaskStatusIsNot(status);

        return taskMapper.convertToDto(task);
    }

    private TaskRepository taskRepository;
private TaskMapper taskMapper;
    @Override
    public void save(TaskDTO taskDTO) {
      taskDTO.setTaskStatus(Status.OPEN);
      taskDTO.setAssignedDate(LocalDate.now());
        taskRepository.save(taskMapper.convertToEntity(taskDTO));
    }

    @Override
    public void delete(Long id) {
Optional<Task> task=taskRepository.findById(id);
if(task.isPresent()){
    task.get().setIsDeleted(true);
    taskRepository.save(task.get());
}

    }

    @Override
    public void update(TaskDTO taskDTO) {
Optional<Task> task=taskRepository.findById(taskDTO.getId());
Task convertedTask=taskMapper.convertToEntity(taskDTO);

    if(task.isPresent()){
        convertedTask.setTaskStatus(task.get().getTaskStatus());
        convertedTask.setAssignedDate(task.get().getAssignedDate());
        taskRepository.save(convertedTask);
    }
    }

    @Override
    public List<TaskDTO> listAllTasks() {
   List<Task>taskList =taskRepository.findAll();

        return taskList.stream().map(taskMapper::convertToDto).collect(Collectors.toList());
    }

    @Override
    public TaskDTO findById(Long id) {

Optional<Task>task=taskRepository.findById(id);
if(task.isPresent()){
    return taskMapper.convertToDto(task.get());
}
        return null;
    }

    @Override
    public List<TaskDTO> findAllTasksByStatus(Status status) {
       List<Task>taskList=taskRepository.findAllByTaskStatus(status);

        return null;
    }

    @Override
    public int totalNonCompletedTask(String projectCode) {
        return taskRepository.totalNonCompletedTask();
    }

    @Override
    public int totalCompletedTask(String projectCode) {
        return taskRepository.totalCompletedTask();
    }
}
