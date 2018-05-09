package com.junit.test;

import com.entities.User;
import com.repositories.UserRepository;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class GetProfileTest {
	
	@Autowired
	private UserRepository userRepository;

	@Test
	public void test() {
		User expected = new User();
		expected.setId(7);
		expected.setLastName("Mujje");
		expected.setFirstName("Manogna");
		expected.setLocation("San Diego");
		expected.setCountry("United States");
		expected.setPhone("9090909009");
		expected.setEmail("manom@gmail.com");
		assertSame(expected, userRepository.findByUsername("manom") );
	}

}

