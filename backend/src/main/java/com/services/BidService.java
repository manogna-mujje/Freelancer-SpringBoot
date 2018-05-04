package com.services;

import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import com.repositories.BidRepository;
import com.entities.Bid;
import com.entities.Project;

import java.util.List;

@Component
public class BidService {
	
	@Autowired
	private BidRepository bidRepository;
	
	public void addBid(Bid bid) {
		bidRepository.save(bid);
	}
	
	public Iterable<Bid> getAllBids(Integer projectId){
        return bidRepository.findByProjectId(projectId);
    }
	
	public Iterable<Bid> getMyBids(String freelancer){
        return bidRepository.findByFreelancer(freelancer);
    }
	
	public List<Bid> getBid(Integer id){
		return bidRepository.findById(id);
	}

}
