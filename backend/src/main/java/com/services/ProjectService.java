package com.services;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import com.repositories.ProjectRepository;
import com.entities.Project;

import java.util.List;

@Component
public class ProjectService {
	
	@Autowired
	private ProjectRepository projectRepository;
	
	public void addProject(Project project) {
		projectRepository.save(project);
	}
	
	public Iterable<Project> getAllProjects(){
        return projectRepository.findByStatus("open");
    }
	
	public Iterable<Project> getMyProjects(String employer){
        return projectRepository.findByEmployer(employer);
    }
	
	public List<Project> getProject(Integer id){
		return projectRepository.findById(id);
	}

}
