package com.controllers;

import java.io.IOException;
import java.sql.SQLException;

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

import com.helpers.TrippleDes;

import com.entities.User;
import com.services.UserService;


@Controller
@CrossOrigin(origins = "http://localhost:3001")
//@RequestMapping(path="/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private TrippleDes td;
	
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
    public @ResponseBody Iterable<User> getAllUsers() {
        // This returns a JSON with the users
        return userService.getAllUsers();
    }
	

    @PostMapping(path="/login",consumes = MediaType.APPLICATION_JSON_VALUE)
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
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<?> logout(HttpSession session) {
        System.out.println(session.getAttribute("name"));
        session.invalidate();
        return  new ResponseEntity(HttpStatus.OK);
    }
    
    @PostMapping(value="/checkSession")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<?> checkSession(HttpSession session) {
		System.out.println("Session check started");
		 System.out.println(session.getAttribute("name"));
		if(session.getAttribute("name") != null) {
			return new ResponseEntity(HttpStatus.OK);
		} else {
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		}
        
    }
    
    @GetMapping(path="/")
    public @ResponseBody String get() throws ClassNotFoundException, SQLException, IOException, InstantiationException, IllegalAccessException {
  	  return "App is alive!!!";
    }
}
