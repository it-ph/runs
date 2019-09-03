package com.personiv.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.personiv.dao.NotifDao;
import com.personiv.model.Notification;

@Service
public class NotifService {

	@Autowired
	private NotifDao notifDao;

	public List<Notification> getNotifications(Long id){
		return notifDao.getNotification(id);
	}

	public void viewNotif(Notification notif) {
		notifDao.viewNotif(notif);
	}
	
	public void viewAllNotif(List<Notification> notif) {
		notifDao.viewAllNotif(notif);
	}
}
