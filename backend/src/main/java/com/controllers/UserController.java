package com.controllers;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.helpers.TrippleDes;

import com.entities.User;
import com.services.UserService;


@Controller
//@RequestMapping(path="/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private TrippleDes td;
	
	@CrossOrigin(origins = "http://localhost:8080")
	@PostMapping(path="/signup",consumes = MediaType.APPLICATION_JSON_VALUE) // Map ONLY POST Requests
	public  ResponseEntity<?> addNewUser (@RequestBody User user) {
    // @ResponseBody means the returned String is the response, not a view name
	// @RequestParam means it is a parameter from the GET or POST request
	 	String encrypted = td.encrypt(user.getPassword());
	 	user.setPassword(encrypted);
        userService.addUser(user);
        System.out.println("Saved the user");
        return new ResponseEntity(null,HttpStatus.CREATED);
   }
	
	
	@GetMapping(path="/all",produces = MediaType.APPLICATION_JSON_VALUE)
	@CrossOrigin(origins = "http://localhost:8080")
    public @ResponseBody Iterable<User> getAllUsers() {
        // This returns a JSON with the users
        return userService.getAllUsers();
    }
	
	
	@GetMapping(path="/profile",produces = MediaType.APPLICATION_JSON_VALUE)
	@CrossOrigin(origins = "http://localhost:8080")
    public @ResponseBody List<User> getProfile(HttpSession session) {
		System.out.println(session.getAttribute("name"));
        return userService.getProfile(session.getAttribute("name").toString());
    }
	
	@PostMapping(path="/updateProfile",consumes = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin(origins = "http://localhost:8080")
    public ResponseEntity<Object> updateProfile(@RequestBody User user, HttpSession session)
    {
	 	if(userService.updateProfile(user, session.getAttribute("name").toString())) {
        	return new ResponseEntity(HttpStatus.OK);
        }
        else {
        	 return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }
	 
	 
    @PostMapping(path="/login",consumes = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin(origins = "http://localhost:8080")
    public ResponseEntity<?> login(@RequestBody String user, HttpSession session)
    {
        JSONObject jsonObject = new JSONObject(user);
        session.setAttribute("name",jsonObject.getString("username"));
        String encrypted = td.encrypt(jsonObject.getString("password"));
        if(userService.login(jsonObject.getString("username"), encrypted)) {
        	return new ResponseEntity(HttpStatus.OK);
        }
        else {
        	 return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(value = "/logout")
    @CrossOrigin(origins = "http://localhost:8080")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<?> logout(HttpSession session) {
        System.out.println(session.getAttribute("name"));
        session.invalidate();
        return  new ResponseEntity(HttpStatus.OK);
    }
    
	
    @PostMapping(value="/checkSession")
    @CrossOrigin(origins = "http://localhost:8080")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<?> checkSession(HttpSession session) {
		System.out.println("Session check started");
		 System.out.println(session.getAttribute("name"));
		 System.out.println(userService.getProfile(session.getAttribute("name").toString()));
		if(session.getAttribute("name") != null) {
			return new ResponseEntity(userService.getProfile(session.getAttribute("name").toString()), HttpStatus.OK);
		} else {
			return new ResponseEntity(null, HttpStatus.BAD_REQUEST);
		}
        
    }
    
	@CrossOrigin(origins = "http://localhost:8080")
    @GetMapping(path="/")
    public @ResponseBody String get() throws ClassNotFoundException, SQLException, IOException, InstantiationException, IllegalAccessException {
  	  return "App is alive!!!";
    }
}
