package com.personiv.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.MediaType;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.personiv.model.User;
import com.personiv.service.UserService;

@RestController
public class GalleryController {
	
	@Autowired
	private UserService userService;
	

	
	@RequestMapping(path= "/gallery", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<User> getUsers(){

		return userService.getUsers();
	
	}
	


	
	
	

}
