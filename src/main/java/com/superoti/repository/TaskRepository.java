package com.superoti.repository;

import com.superoti.model.Task;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface TaskRepository extends CrudRepository<Task,Long>{
    List<Task> getByDataRemocaoIsNull();

}
