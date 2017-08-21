package com.superoti.service;

import com.superoti.model.Task;
import com.superoti.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    @Autowired
    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public Task getById(Long id){
        verificaSeTaskExiste(id);
        return taskRepository.findOne(id);
    }

    public Task save(Task task){
        task.setDataCriacao(LocalDateTime.now());
        return taskRepository.save(task);
    }

    public Task update(Task task){
        verificaSeTaskExiste(task.getId());
        task.setDataEdicao(LocalDateTime.now());
        return taskRepository.save(task);
    }

    public void delete(Long id){
        verificaSeTaskExiste(id);
        Task task = taskRepository.findOne(id);
        task.setDataRemocao(LocalDateTime.now());
        taskRepository.save(task);
    }

    public List<Task> getAll(){
        return taskRepository.getByDataRemocaoIsNull();
    }

    private void verificaSeTaskExiste(Long id){
        if(!taskRepository.exists(id)){
            throw new ResourceNotFoundException("Nenhuma task encontrada para o id ",null);
        }
    }
}
