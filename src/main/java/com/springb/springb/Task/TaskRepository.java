package com.springb.springb.Task;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactory;

public interface TaskRepository extends JpaRepository<Task, Long> {
    
}
