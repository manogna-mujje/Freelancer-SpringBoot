package com.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import com.repositories.UserRepository;
import com.entities.User;
import org.springframework.stereotype.Component;

@Component
public class UserService {
	@Autowired
	private UserRepository userRepository;
	
	public void addUser(User user) {
		userRepository.save(user);
	}
	
	public Iterable<User> getAllUsers(){
        return userRepository.findAll();
    }
	
	public boolean login(String username,String password){
		List<User> list = userRepository.findByUsernameAndPassword(username, password);
		if(list.isEmpty()) {
			return false;
		}
		return true;
	}
}
