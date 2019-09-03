package com.personiv.controller;

import java.security.Principal;

import java.util.List;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.personiv.model.ErrorResponse;
import com.personiv.model.Registration;
import com.personiv.model.User;
import com.personiv.service.UserService;

@RestController
public class UserController {
	
	@Autowired
	private UserService userService;
	

	
	@RequestMapping(path= "/users", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<User> getUsers(){

		return userService.getUsers();
	
	}
	

	@RequestMapping(path= "/users/resetPassword", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> resetPasdword(@RequestBody User user) throws MessagingException{
		
		System.out.println(user);
		userService.resetPassword(user);
		
		return ResponseEntity.ok(user);
	}
	
	
	@RequestMapping(path= "/users/testPassword")
	public String testPassword() {
		return userService.testPassword();
	}
	
	@RequestMapping(path= "/users/disable", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> disableUser(@RequestBody User user){
		
		userService.disableUser(user);
		return ResponseEntity.ok(user);
	} 
	
	@RequestMapping(path= "/users/enable", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> enableUser(@RequestBody User user){
		
		
			userService.enableUser(user);
			return ResponseEntity.ok(user);
		

	} 
	
	@RequestMapping(path= "/users/delete", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> deleteUser(@RequestBody User user){
		
		try {
			userService.deleteUser(user);
			return ResponseEntity.ok(user);}
		catch(DataIntegrityViolationException e) {
			e.printStackTrace();
			return ResponseEntity.status(500).body(new ErrorResponse("Cannot delete, data has an existing record associated"));
		}
	}
	
	@RequestMapping(path= "/users/registrations/delete", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> deleteUserRegistration(@RequestBody Registration reg){
		
		try {
			userService.deleteUserRegistration(reg);
			return ResponseEntity.ok(reg);}
		catch(DataIntegrityViolationException e) {
			e.printStackTrace();
			return ResponseEntity.status(500).body(new ErrorResponse("Cannot delete, data has an existing record associated"));
		}
	}
	

	
	@RequestMapping(path = "/users/changePassword", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> chagePassword(@RequestBody User user,Principal principal){
		
		   user.setEmail(principal.getName());
		   userService.changePassword(user);
		   return ResponseEntity.ok(user);
		   
	 }
	
	
	
	

}
