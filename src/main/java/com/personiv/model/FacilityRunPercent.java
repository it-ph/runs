package com.personiv.model;


public class FacilityRunPercent {
	private Long eventId;
	public Long getEventId() {
		return eventId;
	}
	public void setEventId(Long eventId) {
		this.eventId = eventId;
	}
	public String getFacility() {
		return facility;
	}
	public void setFacility(String facility) {
		this.facility = facility;
	}
	public Double getTotalCategoryDistance() {
		return totalCategoryDistance;
	}
	public void setTotalCategoryDistance(Double totalCategoryDistance) {
		this.totalCategoryDistance = totalCategoryDistance;
	}
	public Double getTotalUserDistance() {
		return totalUserDistance;
	}
	public void setTotalUserDistance(Double totalUserDistance) {
		this.totalUserDistance = totalUserDistance;
	}
	private String facility;
	
	public Double getBike() {
		return bike;
	}
	public void setBike(Double bike) {
		this.bike = bike;
	}
	public Double getRun() {
		return run;
	}
	public void setRun(Double run) {
		this.run = run;
	}
	public Double getDua() {
		return dua;
	}
	public void setDua(Double dua) {
		this.dua = dua;
	}
	private Double bike;
	private Double duacountbike;
	public Double getDuacountbike() {
		return duacountbike;
	}
	public void setDuacountbike(Double duacountbike) {
		this.duacountbike = duacountbike;
	}
	private Double bikes;
	public Double getBikes() {
		return bikes;
	}
	public void setBikes(Double bikes) {
		this.bikes = bikes;
	}
	private Double run;
	private Double runsumcount;
	public Double getRunsumcount() {
		return runsumcount;
	}
	public void setRunsumcount(Double runsumcount) {
		this.runsumcount = runsumcount;
	}
	private Double dua;
	private Double runapproved;
	private Double bikeapproved;
	public Double getBikeapproved() {
		return bikeapproved;
	}
	public void setBikeapproved(Double bikeapproved) {
		this.bikeapproved = bikeapproved;
	}
	public Double getRunapproved() {
		return runapproved;
	}
	public void setRunapproved(Double runapproved) {
		this.runapproved = runapproved;
	}
	private Double frun;
	public Double getFrun() {
		return frun;
	}
	public void setFrun(Double frun) {
		this.frun = frun;
	}
	private Double totalCategoryDistance;
	private Double totalUserDistance;
}
