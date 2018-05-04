package com.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class User {
	
	 @Id
	 @GeneratedValue(strategy=GenerationType.AUTO)
	 private Integer id;

	 private String email;
	 
	 private String username;

	 private String password;
	 
	 private String firstName;
	 
	 private String lastName;
	 
	 private String location;
	 
	 private String country;
	 
	 private String phone;
	 
	 private String image;
	 
	 public Integer getId() {
	        return id;
	    }
	
	 public void setId(Integer id) {
	        this.id = id;
	    }
	 
	 public String getUsername() {
	        return username;
	    }

	 public void setUsername(String username) {
	        this.username = username;
	    }
	 
	 public String getPassword() {
		 	return password;
	    }

	 public void setPassword(String password) {
		 	this.password = password;
	    }
	
	 public String getFirstName() {
	        return firstName;
	    }
	
	 public void setFirstName(String firstName) {
	        this.firstName = firstName;
	    }
	 
	 public String getLastName() {
	        return lastName;
	    }
	
	 public void setLastName(String lastName) {
	        this.lastName = lastName;
	    }
	
	 public String getEmail() {
	        return email;
	    }
	
	 public void setEmail(String email) {
	        this.email = email;
	    }
	 
	 public String getLocation() {
	        return location;
	    }
	
	 public void setLocation(String location) {
	        this.location = location;
	    }
	 
	 public String getCountry() {
	        return country;
	    }
	
	 public void setCountry(String country) {
	        this.country = country;
	    }
	 public String getPhone() {
	        return phone;
	    }
	
	 public void setPhone(String phone) {
	        this.phone = phone;
	    }
	 
	 public String getImage() {
	        return image;
	    }
	
	 public void setImage(String image) {
	        this.image = image;
	    }
	 
	 @Override
		public String toString() {
			return String.format(
					"User [ id: %d, "
					+ "password: %s, "
					+ "username: %s, "
					+ "firstName: %s," 
					+ "lastName: %s," 
					+ "location: %s," 
					+ "country: %s," 
					+ "email: %s," 
					+ "phone: %s," 
					+ "image: %s ]", 
					id, password, username, firstName, lastName, location, country, email, phone, image);
		}
}
