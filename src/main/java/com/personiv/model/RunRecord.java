package com.personiv.model;

import java.sql.Time;
import java.util.Date;


public class RunRecord {
	private Long id;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public String getFileName2() {
		return fileName2;
	}
	public void setFileName2(String fileName2) {
		this.fileName2 = fileName2;
	}
	public String getFilePath2() {
		return filePath2;
	}
	public void setFilePath2(String filePath2) {
		this.filePath2 = filePath2;
	}
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
	public EventCategory getCategory() {
		return category;
	}
	public void setCategory(EventCategory category) {
		this.category = category;
	}
	public Time getTime() {
		return time;
	}
	public void setTime(Time time) {
		this.time = time;
	}
	public String getVerified() {
		return verified;
	}
	public void setVerified(String verified) {
		this.verified = verified;
	}
	public Float getDistance() {
		return distance;
	}
	public void setDistance(Float distance) {
		this.distance = distance;
	}
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	public Date getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}
	public String getCategs() {
		return categs;
	}
	public void setCategs(String categs) {
		this.categs = categs;
	}
	public String getCateg() {
		return categ;
	}
	public void setCateg(String categ) {
		this.categ = categ;
	}
	public String getFacility() {
		return Facility;
	}
	public void setFacility(String facility) {
		Facility = facility;
	}
	
	private String full;
	public String getFull() {
		return full;
	}
	public void setFull(String full) {
		this.full = full;
	}
	public String getGallery() {
		return gallery;
	}
	public void setGallery(String gallery) {
		this.gallery = gallery;
	}


	private String filePath;
	private String gallery;
	
	private String fileName;
	private String filePath2;
	private String fileName2;
	private String Facility;
	private String categ;
	private String categs;
	private User user;
	private RunEvent event;
	private EventCategory category;
	private Time time;
	private String verified;
	private Float distance;
	private Date createdAt;
	private Date updatedAt;
	
}
