package com.junit.test;

import com.repositories.BidRepository;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class GetMyBids {
	
	@Autowired
	private BidRepository bidRepository;

	@Test
	public void test() {
		String expected = "[\n" + 
				"    [\n" + 
				"        4,\n" + 
				"        450,\n" + 
				"        2,\n" + 
				"        4,\n" + 
				"        \"500\",\n" + 
				"        \"Real time web application\",\n" + 
				"        \"Casino Essentials\",\n" + 
				"        \"Sid\",\n" + 
				"        \"open\",\n" + 
				"        \"Core Java, Boostrap\",\n" + 
				"        500\n" + 
				"    ],\n" + 
				"    [\n" + 
				"        2,\n" + 
				"        302,\n" + 
				"        1,\n" + 
				"        2,\n" + 
				"        \"300\",\n" + 
				"        \"Prototype web application\",\n" + 
				"        \"Freelancer\",\n" + 
				"        \"Mango\",\n" + 
				"        \"closed\",\n" + 
				"        \"MySQL, Java\",\n" + 
				"        302\n" + 
				"    ]\n" + 
				"]";
		assertEquals(expected, bidRepository.findByFreelancer("manom"));
	}

}

