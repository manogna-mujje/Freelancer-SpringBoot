package com.repositories;

import com.entities.Project;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository

public interface ProjectRepository extends CrudRepository<Project, Long> {
    List<Project> findById(Integer id);
    List<Project> findByStatus(String status);
    List<Project> findByEmployer(String employer);
}