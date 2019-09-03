package com.personiv.model;

import java.util.Date;

public class RunEventCategory {
	
	private RunEvent runEvent;
	public RunEvent getRunEvent() {
		return runEvent;
	}
	public void setRunEvent(RunEvent runEvent) {
		this.runEvent = runEvent;
	}
	public EventCategory getEventCategory() {
		return eventCategory;
	}
	public void setEventCategory(EventCategory eventCategory) {
		this.eventCategory = eventCategory;
	}
	public boolean isApproved() {
		return approved;
	}
	public void setApproved(boolean approved) {
		this.approved = approved;
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
	private EventCategory eventCategory;
	private boolean approved;
	private Date createdAt;
	private Date updatedAt;
}
