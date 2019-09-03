package com.personiv.model;


import java.util.Date;


public class RunEventDetail {
	

	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public RunEvent getRunEvent() {
		return runEvent;
	}
	public void setRunEvent(RunEvent runEvent) {
		this.runEvent = runEvent;
	}
	public EventCategory getEventCateg() {
		return eventCateg;
	}
	public void setEventCateg(EventCategory eventCateg) {
		this.eventCateg = eventCateg;
	}
	public Entitlement getEntitlement() {
		return entitlement;
	}
	public void setEntitlement(Entitlement entitlement) {
		this.entitlement = entitlement;
	}
	public boolean isApproved() {
		return approved;
	}
	public void setApproved(boolean approved) {
		this.approved = approved;
	}
	public String getShirtSize() {
		return shirtSize;
	}
	public void setShirtSize(String shirtSize) {
		this.shirtSize = shirtSize;
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
	public String getForhim() {
		return forhim;
	}
	public void setForhim(String forhim) {
		this.forhim = forhim;
	}
	
	private User user;
	private RunEvent runEvent;
	private EventCategory eventCateg;
	private Entitlement entitlement;
	private boolean approved;
	private String shirtSize;
	private String forhim;
	


	private Date createdAt;
	private Date updatedAt;
}
