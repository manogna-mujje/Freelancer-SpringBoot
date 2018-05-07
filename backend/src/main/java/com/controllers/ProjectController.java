package com.controllers;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.json.JSONObject;
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
import org.springframework.web.bind.annotation.ResponseStatus;

import com.entities.Project;
import com.services.ProjectService;


@Controller
@CrossOrigin(origins = "*")
public class ProjectController {
	
	@Autowired
	private ProjectService projectService;
	
	@PostMapping(path="/postProject",consumes = MediaType.APPLICATION_JSON_VALUE) // Map ONLY POST Requests
	public  ResponseEntity<?> addProject (@RequestBody Project project, HttpSession session) {
		System.out.println("Add Project API");
		project.setEmployer(session.getAttribute("name").toString());
		project.setStatus("open");
		projectService.addProject(project);
        return new ResponseEntity(null,HttpStatus.CREATED);
   }
	
	//Get All Projects
	@GetMapping(path="/showProjects",produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody List<Object> getAllProjects() {
        return projectService.getAllProjects();
    }
	
	
	//Get My Projects
	@PostMapping(path="/myProjects",produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody List<Object> getMyProjects(HttpSession session) {
		if(session.getAttribute("name") == null) {
			return null;
		}
        return projectService.getMyProjects(session.getAttribute("name").toString());
    }
	
	
	//Get Project By Id
	@PostMapping(path="/showProjectDetails",produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody List<Project> getProject(@RequestBody Project project) {
		System.out.println("Show Project Details API");
		System.out.println(project.getId());
        return projectService.getProject(project.getId());
    }
}
