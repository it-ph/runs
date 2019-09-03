package com.personiv.model;

import java.util.Date;


public class RunProgress {
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public RunEvent getEvent() {
		return event;
	}
	public void setEvent(RunEvent event) {
		this.event = event;
	}
	public EventCategory getEventCategory() {
		return eventCategory;
	}
	public void setEventCategory(EventCategory eventCategory) {
		this.eventCategory = eventCategory;
	}
	public float getDistanceTraveled() {
		return distanceTraveled;
	}
	public void setDistanceTraveled(float distanceTraveled) {
		this.distanceTraveled = distanceTraveled;
	}
	public String getTotalElapseTime() {
		return totalElapseTime;
	}
	public void setTotalElapseTime(String totalElapseTime) {
		this.totalElapseTime = totalElapseTime;
	}
	public String getCateg() {
		return categ;
	}
	public void setCateg(String categ) {
		this.categ = categ;
	}
	public Double getPace() {
		return pace;
	}
	public void setPace(Double pace) {
		this.pace = pace;
	}
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public String getCategs() {
		return categs;
	}
	public void setCategs(String categs) {
		this.categs = categs;
	}
	private int userId;
	public int getuserId() {
		return userId;
	}
	public void setuserId(int userId) {
		this.userId = userId;
	}
	public String getGallery() {
		return gallery;
	}
	public void setGallery(String gallery) {
		this.gallery = gallery;
	}
	private float distanceTraveled;
	//private Time totalElapseTime;
	private String totalElapseTime;
	private Double pace;
	private Date createdAt;
	private RunEvent event;
	private EventCategory eventCategory;
	private String categ;
	private String gallery;
	
	private String categs;
	private User user;
	
}
