package com.superoti.endpoint;

import com.superoti.model.Task;
import com.superoti.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/tasks")
public class TaskEndpoint {

    private final TaskService taskService;


    @Autowired
    public TaskEndpoint(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    public ResponseEntity<?> getAll(){
        return new ResponseEntity<>(taskService.getAll(),HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") Long id){
        return new ResponseEntity<>(taskService.getById(id),HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> save (@Valid @RequestBody Task task){
        return new ResponseEntity<>(taskService.save(task), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<?> update(@Valid @RequestBody Task task){
        return new ResponseEntity<>(taskService.update(task),HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id){
        taskService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
