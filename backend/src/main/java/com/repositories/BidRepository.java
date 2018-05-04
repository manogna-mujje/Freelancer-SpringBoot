package com.repositories;

import com.entities.Bid;
import com.entities.Project;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository

public interface BidRepository extends CrudRepository<Bid, Long> {
    List<Bid> findById(Integer id);
    Iterable<Bid> findByProjectId(Integer projectId);
    List<Bid> findByFreelancer(String freelancer);
}