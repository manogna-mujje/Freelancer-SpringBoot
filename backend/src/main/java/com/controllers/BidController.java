package com.controllers;

//import java.util.List;

import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.entities.Bid;
import com.entities.Project;
import com.services.BidService;


@Controller
@CrossOrigin(origins = "http://localhost:3001")
public class BidController {
	
	@Autowired
	private BidService bidService;
	
	@PostMapping(path="/postBid",consumes = MediaType.APPLICATION_JSON_VALUE) // Map ONLY POST Requests
	public  ResponseEntity<?> addBid (@RequestBody Bid bid, HttpSession session) {
		bid.setFreelancer(session.getAttribute("name").toString());
		bidService.addBid(bid);
        return new ResponseEntity(null,HttpStatus.CREATED);
   }
	
	//Get All Bids of a project
	@PostMapping(path="/showBids",produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody Iterable<Bid> getAllBids(@RequestBody Bid bid) {
        return bidService.getAllBids(bid.getProjectId());
    }
	
	//Get My Bids
		@PostMapping(path="/myBids",produces = MediaType.APPLICATION_JSON_VALUE)
	    public @ResponseBody Iterable<Bid> getMyBids(HttpSession session) {
			if(session.getAttribute("name") == null) {
				return null;
			}
	        return bidService.getMyBids(session.getAttribute("name").toString());
	    }
	
//	//Get Bid By Id
//	@PostMapping(path="/showProjectDetails",produces = MediaType.APPLICATION_JSON_VALUE)
//    public @ResponseBody List<Project> getProject(@RequestBody Project project) {
//        return bidService.getProject(project.getId());
//    }
}

