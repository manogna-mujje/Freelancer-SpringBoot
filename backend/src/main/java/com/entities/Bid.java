package com.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Bid {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	
	private Integer projectId;
	
	private String employer;
	
	private String freelancer;
	
	private float bidAmount;
	
	public Integer getId() {
        return id;
    }

	public void setId(Integer id) {
        this.id = id;
    }
	
	public Integer getProjectId() {
        return projectId;
    }

	public void setProjectId(Integer id) {
        this.projectId = id;
    }
	
	public String getEmployer() {
        return employer;
    }

	public void setEmployer(String employer) {
        this.employer = employer;
    }
	
	public String getFreelancer() {
        return freelancer;
    }

	public void setFreelancer(String freelancer) {
        this.freelancer = freelancer;
    }
	
	public float getBidAmount() {
		return bidAmount;
	}
	
	public void setBidAmount(float bidAmount) {
		this.bidAmount = bidAmount;
	}

	@Override
	public String toString(){
		return String.format("Project [id=%s, ProjectId=%s, Employer=%s, Freelancer=%s, BidAmount=%ss]",
				id, projectId, employer, freelancer, bidAmount);
	}
}
