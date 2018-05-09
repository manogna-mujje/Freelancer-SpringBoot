package com.junit.test;

import com.entities.Project;
import com.repositories.ProjectRepository;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class GetProjectTest {
	
	@Autowired
	private ProjectRepository projectRepository;

	@Test
	public void test() {
		Project sample = new Project();
		sample.setId(7);
		Project expected = new Project();
		expected.setId(7);
		expected.setEmployer("manom");
		expected.setProjectName("Kayak Demo");
		expected.setDescription("Prototype web application");
		expected.setBudget("1000");
		expected.setSkills("MySQL, React.js Node.js");
		assertSame(expected, projectRepository.findById(7) );
	}

}
