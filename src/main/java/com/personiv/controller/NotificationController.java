package com.personiv.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.personiv.model.Notification;
import com.personiv.model.User;
import com.personiv.service.NotifService;
import com.personiv.service.UserService;

@RestController
public class NotificationController {
	
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private NotifService notifService;
	
	
	@RequestMapping(path = "/notifications", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Notification> getNotification(Principal principal){
		User user  = userService.getUserByUsername(principal.getName());
		
		return notifService.getNotifications(user.getId());
	}
	
	@RequestMapping(path = "/notifications/view", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes =  MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> viewNotif(@RequestBody Notification notif){
		
		notifService.viewNotif(notif);
		return ResponseEntity.ok(notif);
	}
	
	@RequestMapping(path = "/notifications/viewAll", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE,consumes =  MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> viewNotif(@RequestBody List<Notification> notif){
		notifService.viewAllNotif(notif);
		return ResponseEntity.ok(null);
	}
}
