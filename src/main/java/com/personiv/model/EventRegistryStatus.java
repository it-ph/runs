package com.personiv.model;

import java.util.Date;


public class EventRegistryStatus {
	private String fullName;
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getFacility() {
		return facility;
	}
	public void setFacility(String facility) {
		this.facility = facility;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getEventCategory() {
		return eventCategory;
	}
	public void setEventCategory(String eventCategory) {
		this.eventCategory = eventCategory;
	}
	public Long getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}
	public String getShirtSize() {
		return shirtSize;
	}
	public void setShirtSize(String shirtSize) {
		this.shirtSize = shirtSize;
	}
	public Long getEventId() {
		return eventId;
	}
	public void setEventId(Long eventId) {
		this.eventId = eventId;
	}
	public Date getEventRegistered() {
		return eventRegistered;
	}
	public void setEventRegistered(Date eventRegistered) {
		this.eventRegistered = eventRegistered;
	}
	public Date getEventRegStart() {
		return eventRegStart;
	}
	public void setEventRegStart(Date eventRegStart) {
		this.eventRegStart = eventRegStart;
	}
	public Date getEventRegEnd() {
		return eventRegEnd;
	}
	public void setEventRegEnd(Date eventRegEnd) {
		this.eventRegEnd = eventRegEnd;
	}
	public Date getEventRunStart() {
		return eventRunStart;
	}
	public void setEventRunStart(Date eventRunStart) {
		this.eventRunStart = eventRunStart;
	}
	public Date getEventRunEnd() {
		return eventRunEnd;
	}
	public void setEventRunEnd(Date eventRunEnd) {
		this.eventRunEnd = eventRunEnd;
	}
	private String facility;
	private String email;
	private String eventCategory;
	private Long categoryId;
	private String shirtSize;
	private Long eventId;
	private Date eventRegistered;
	private Date eventRegStart;
	private Date eventRegEnd;
	private Date eventRunStart;
	private Date eventRunEnd;
	
}
