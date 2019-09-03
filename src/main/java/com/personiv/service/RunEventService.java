package com.personiv.service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.multipart.MultipartFile;

import com.personiv.dao.RunEventDao;
import com.personiv.model.Entitlement;
import com.personiv.model.EventCategory;
import com.personiv.model.EventRegistryStatus;
import com.personiv.model.FacilityRunPercent;
import com.personiv.model.RunEvent;
import com.personiv.model.RunEventCategory;
import com.personiv.model.RunEventDetail;
import com.personiv.model.RunProgress;
import com.personiv.model.RunRecord;
import com.personiv.model.ServerTime;

@Service
public class RunEventService {
	
	@Autowired
	private RunEventDao runEventDao;
	
	public RunEvent getCurrentRun() {
		return runEventDao.getCurrentRun();
	}

	public RunEventDetail getActiveReg(String name) {
		return runEventDao.getActiveReg(name);
	}
	
	public RunEventDetail getActiveRun2(String name) {
		return runEventDao.getActiveRun2(name);
	}
	
	public List<RunEventDetail> getParticipants(){
		
		return runEventDao.getParticipants();
	}

	public List<RunRecord> getRunRecords(String username) {
		return runEventDao.getRunRecords(username);
	}

	public List<RunRecord> getRunRecordsByEventByUser(Long eventId, Long userId){
		return runEventDao.getRunRecordsByEventByUser(eventId, userId);
	}
	public List<Entitlement> getEntitlements(String name) {
		return runEventDao.getEntitlements(name);
	}

	public List<EventCategory> getCategories() {
	
		return runEventDao.getCategories();
	}

	public void registerRun(RunEventDetail runEventDetail) {
		runEventDao.registerRun(runEventDetail);
		
	}

	public void approveRegistration(RunEventDetail runEventDetail) {
		runEventDao.approveRegistration(runEventDetail);
		
	}

	public RunProgress getUserProgress(RunEvent event,String name) {
		return runEventDao.getUserProgress(event,name);
	}
	
	public RunProgress getUserProgressById(Long eventId, Long userId) {
		return runEventDao.getUserProgressById(eventId, userId);
	}
	public void addRecord(RunProgress record, MultipartFile file, MultipartFile files) throws IOException {
		runEventDao.addRecord(record,file,files);
		
	}

	public RunRecord getRunProgress(Long id) {
		return runEventDao.getRunProgress(id);
	}

	public List<RunRecord> getUnverifiedRecords() {
		
		return runEventDao.getUnverifiedRecords();
	}

	public void approveRecord(RunRecord record) {
		runEventDao.approveRecord(record);
		
	}
	public void disapproveRecord(RunRecord record) {
		runEventDao.disapproveRecord(record);
		
	}

	public List<RunRecord> getRecords() {
		return runEventDao.getRecords();
	}
	
	public List<RunProgress> getAllRunProgress(){
		return runEventDao.getAllRunProgress();
	}
	public List<RunProgress> getAllProgressByEvent(Long eventId){
		return runEventDao.getAllProgressByEvent(eventId);
	}

	public List<RunProgress> getProgressByEventByCategory(Long event, Long id) {
	
		return runEventDao.getProgressByEventByCategory(event,id);
	}
	
	public List<RunProgress> getAllProgressByEventyByFacility(Long eventId, String facility){
		return runEventDao.getAllProgressByEventByFacility(eventId, facility);
	}
	
	public List<RunProgress> getProgressByGender(Long event, Long id) {
		
		return runEventDao.getProgressByGender(event,id);
	}
	public List<EventRegistryStatus> getEventRegistryStatusByEventByFacility(Long event, String facility) {
		// TODO Auto-generated method stub
		return runEventDao.getEventRegistryStatusByEventByFacility(event, facility);
	}

	public List<EventRegistryStatus> getEventRegistryStatusByEvent(Long event) {
		return runEventDao.getEventRegistryStatusByEvent(event);
	}

	public List<RunEvent> getRunEvents() {
		return runEventDao.getRunEvents();
	}

	public void changeRegistrationCategory(RunEventDetail runEventDetail) {
		runEventDao.changeRegistrationCategory(runEventDetail);
		
	}
	
	public List<FacilityRunPercent> getFacilityRunPercentByEvent(Long eventId){
		return runEventDao.getFacilityRunPercentByEvent(eventId);
	}
	
	public FacilityRunPercent getFacilityRunPercentByEvent(Long eventId, String facility){
		return runEventDao.getFacilityRunPercentByEvent(eventId, facility);
	}

}
