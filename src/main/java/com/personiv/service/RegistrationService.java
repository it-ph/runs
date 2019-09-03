package com.personiv.service;

import java.util.List;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.personiv.dao.RegistrationDao;
import com.personiv.model.Registration;
import com.personiv.model.RunEventDetail;

@Service
public class RegistrationService {
	
	@Autowired
	private RegistrationDao regDao;
	
	public List<Registration> getRegistrations(){
		return regDao.getRegistrations();
	}

	public void approve(Registration reg) throws MessagingException {
		regDao.approve(reg);
		
	}

	public void register(Registration reg) {
		regDao.register(reg);
		
	}
	public Boolean isRegistered(String username) {
		return regDao.isRegistered(username);
	}

	public void resetPass(Registration reg) {
		regDao.resetPass(reg);
		
	}

	public void deleteRegistration(RunEventDetail reg) {
		regDao.deleteRegistration(reg);
		
	}
	
	
}
