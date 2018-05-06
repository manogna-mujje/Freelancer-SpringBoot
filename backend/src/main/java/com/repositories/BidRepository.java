package com.repositories;

import com.entities.Bid;
import com.entities.Project;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository

public interface BidRepository extends CrudRepository<Bid, Long> {
    List<Bid> findById(Integer id);
    Iterable<Bid> findByProjectId(Integer projectId);
    
    @Query(value = "select p.id, budget, description, project_name, p.employer, p.status, skills, b.bid_amount from Project as p join Bid as b where p.id = b.project_id AND b.freelancer = :freelancer", nativeQuery = true)
    List<Object> findByFreelancer(@Param ("freelancer") String freelancer);

	@Query(value = "select AVG(bid_amount) from Bid where project_id = :projectId", nativeQuery = true)
   	float getAvgBid(@Param ("projectId") Integer projectId);
    
}