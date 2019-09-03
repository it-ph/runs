package com.personiv.service;

import java.util.List;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.personiv.dao.UserDao;
import com.personiv.model.Registration;
import com.personiv.model.User;

@Service
public class UserService {

	@Autowired
	private UserDao userDao;
	
	

	public void resetPassword(User user) throws MessagingException {
		userDao.resetPassword(user);
		
	}
	public void disableUser(User user) {
		userDao.disableUser(user);
	}

	public List<User> getUsers() {
		return userDao.getUsers();
	}
	
	public String testPassword() {
		return userDao.testPassword();
	}

	public void changePassword(User user) {
		userDao.changePassword(user);
		
	}
	
	public User getUserByUsername(String username) {
		return userDao.getUserByUsername(username);
	}
	public void enableUser(User user) {
		userDao.enableUser(user);
		
	}
	public void deleteUser(User user) {
		userDao.deleteUser(user);
		
	}
	public void deleteUserRegistration(Registration reg) {
		userDao.deleteUserRegistration(reg);
	}
}
