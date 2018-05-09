package com.junit.test;

import com.repositories.ProjectRepository;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class GetMyProjects {
	
	@Autowired
	private ProjectRepository projectRepository;

	@Test
	public void test() {
		String expected = "[" +
				"[" +
			        "7," +
			        "1100," +
			        "1," +
			        "7," +
			        "1000," + 
			        "Prototype web application," + 
			        "Kayak Demo," +
			        "manom," +
			        "open," +
			        "MySQL, React.js Node.js" +
			 "]," +
			"]";
		assertEquals(expected, projectRepository.findByEmployer("manom"));
	}

}

