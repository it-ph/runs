package com.personiv.model;

import java.util.Date;



public class RunEvent {
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Date getRegStart() {
		return regStart;
	}
	public void setRegStart(Date regStart) {
		this.regStart = regStart;
	}
	public Date getRegEnd() {
		return regEnd;
	}
	public void setRegEnd(Date regEnd) {
		this.regEnd = regEnd;
	}
	public Date getRunStart() {
		return runStart;
	}
	public void setRunStart(Date runStart) {
		this.runStart = runStart;
	}
	public Date getRunEnd() {
		return runEnd;
	}
	public void setRunEnd(Date runEnd) {
		this.runEnd = runEnd;
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
	private Long id;
	private Date regStart;
	private Date regEnd;
	private Date runStart;
	private Date runEnd;
	private Date createdAt;
	private Date updatedAt;
	
}
