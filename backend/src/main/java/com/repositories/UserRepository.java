package com.repositories;

import com.entities.User;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository

public interface UserRepository extends CrudRepository<User, Long> {
	
    List<User> findByUsernameAndPassword(String username,String password);
    
    List<User> findByUsername(String username);
    
    @Modifying
	@Query("update User set first_name = ?1, last_name = ?2, country = ?3, location = ?4, phone = ?5 where username = ?6")
	void setUserInfoByUsername(String firstName, String lastName, String country, String location, String phone, String userName);
}