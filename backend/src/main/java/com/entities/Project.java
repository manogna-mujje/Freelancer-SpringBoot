package com.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Project {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	
	private String employer;
	
	private String projectName;
	
	private String description;
	
	private String budget;
	
	private String status;
	
	private String skills;
	
	public Integer getId() {
        return id;
    }

	public void setId(Integer id) {
        this.id = id;
    }
 
	public String getEmployer() {
        return employer;
    }

	public void setEmployer(String employer) {
        this.employer = employer;
    }
	
	public String getProjectName() {
        return projectName;
    }

	public void setProjectName(String projectName) {
        this.projectName = projectName;
    }
	
	public String getDescription() {
        return description;
    }

	public void setDescription(String description) {
        this.description = description;
    }
	
	public String getBudget() {
        return budget;
    }

	public void setBudget(String budget) {
        this.budget = budget;
    }
	
	public String getStatus() {
        return status;
    }

	public void setStatus(String status) {
        this.status = status;
    }
	
	public String getSkills() {
        return skills;
    }

	public void setSkills(String skills) {
        this.skills = skills;
    }
	
	@Override
	public String toString(){
		return String.format("Project [id=%s, Name=%s, Description=%s, Budget=%s, Status=%s, Skills=%s, Employer=%s]",
				id, projectName, description, budget, status, skills, employer );
	}
	
}
