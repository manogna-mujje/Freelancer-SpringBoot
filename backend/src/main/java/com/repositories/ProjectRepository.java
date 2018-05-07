package com.repositories;

import com.entities.Project;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository

public interface ProjectRepository extends CrudRepository<Project, Long> {
    List<Project> findById(Integer id);
    
    
    @Query(value = "SELECT \n" + 
    		"*\n" + 
    		"FROM\n" + 
    		"(select b.project_id, AVG(bid_amount), COUNT(b.id) from Bid as b GROUP BY b.project_id) t1\n" + 
    		"RIGHT JOIN\n" + 
    		"(select p.id, budget, description, project_name, p.employer, status, skills from Project as p where status = :status) t2\n" + 
    		"ON t1.project_id = t2.id", nativeQuery = true)
    List<Object> findByStatus(@Param ("status") String status);
    
    
    @Query(value = "SELECT \n" + 
    		"*\n" + 
    		"FROM\n" + 
    		"(select b.project_id, AVG(bid_amount), COUNT(b.id) from Bid as b GROUP BY b.project_id) t1\n" + 
    		"RIGHT JOIN\n" + 
    		"(select p.id, budget, description, project_name, p.employer, status, skills from Project as p where status = \"open\" AND employer = :employer) t2\n" + 
    		"ON t1.project_id = t2.id", nativeQuery = true)
    List<Object> findByEmployer(@Param ("employer") String employer);
    
}