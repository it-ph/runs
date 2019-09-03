package com.personiv.controller;

import java.io.IOException;
import java.security.Principal;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.personiv.model.EmptyObject;
import com.personiv.model.Entitlement;
import com.personiv.model.ErrorResponse;
import com.personiv.model.EventCategory;
import com.personiv.model.EventRegistryStatus;
import com.personiv.model.FacilityRunPercent;
import com.personiv.model.RunEvent;
import com.personiv.model.RunEventCategory;
import com.personiv.model.RunEventDetail;
import com.personiv.model.RunProgress;
import com.personiv.model.RunRecord;
import com.personiv.model.ServerTime;
import com.personiv.service.FileService;
import com.personiv.service.RunEventService;

@RestController
public class RunEventController {

	private final Log logger = LogFactory.getLog(this.getClass());
	
	@Autowired
	private FileService fileService;
	
	@Autowired
	private RunEventService runService;
	
	@RequestMapping(path = "/run_event", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)	   
	public ResponseEntity<?> getRunEvents(){
		return ResponseEntity.ok(runService.getRunEvents());	
	}
	
	@RequestMapping(path = "/run_event/current", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)	   
	public ResponseEntity<?> getCurrentRun(){
		try {
			RunEvent run = runService.getCurrentRun();
			
			return ResponseEntity.ok(run);
		}
		catch(EmptyResultDataAccessException e) {
			
			return ResponseEntity.ok(new EmptyObject());
		}
	}
	
	
	@RequestMapping(path = "/run_event/register", method = RequestMethod.POST, consumes=  MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> registerRun(@RequestBody RunEventDetail runEventDetail){
		
		runService.registerRun(runEventDetail);
		
		return ResponseEntity.ok(runEventDetail);
		
	}
	
	@RequestMapping(path = "/run_event/register", method = RequestMethod.PUT, consumes=  MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> approveRegistration(@RequestBody RunEventDetail runEventDetail){
		
		runService.approveRegistration(runEventDetail);
		
		return ResponseEntity.ok(runEventDetail);
		
	}
	
	@RequestMapping(path = "/run_event/registration/change_category", method = RequestMethod.PUT, consumes=  MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> changeRegistrationCategory(@RequestBody RunEventDetail runEventDetail){
		
		runService.changeRegistrationCategory(runEventDetail);
		return ResponseEntity.ok(runEventDetail);
		
	}
	
	@RequestMapping(path = "/run_event/active_run", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)	   
	public ResponseEntity<?> getActiveRun(){
		
		
		//System.out.println(principal.getName());
		
		try {
			//RunEventDetail runEvent = runService.getActiveRun(principal.getName());
			RunEvent runEvent = runService.getCurrentRun();
			
			return ResponseEntity.ok(runEvent);
			
		}catch(EmptyResultDataAccessException e) {
			
			return ResponseEntity.ok(new EmptyObject());
		}
		
		
	
	}
	
	@RequestMapping(path = "/run_event/registry_status/{event}/{facility}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getEventRegistryStatusByEventByFacility(@PathVariable("event") Long event,@PathVariable("facility") String facility){
		
		List<EventRegistryStatus> registryStatus = runService.getEventRegistryStatusByEventByFacility(event,facility);
		
		return ResponseEntity.ok(registryStatus);
		
	}
	
	@RequestMapping(path = "/run_event/registry_status/{event}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getEventRegistryStatusByEvent(@PathVariable("event") Long event){
		
		List<EventRegistryStatus> registryStatus = runService.getEventRegistryStatusByEvent(event);
		
		return ResponseEntity.ok(registryStatus);
		
	}

	@RequestMapping(path = "/run_event/active_reg", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)	   
	public ResponseEntity<?> getActiveReg(Principal principal){
		
		
		//System.out.println(principal.getName());
		
		try {
			RunEventDetail runEvent = runService.getActiveReg(principal.getName());
	
			
			return ResponseEntity.ok(runEvent);
			
		}catch(EmptyResultDataAccessException e) {
			
			return ResponseEntity.ok(new EmptyObject());
		}
		
		
	
	}
	
	@RequestMapping(path = "/run_event/entitlements", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)	   
	public ResponseEntity<?> getEntitlements(Principal principal){
		
		try {
			List<Entitlement> ent = runService.getEntitlements(principal.getName());
			return ResponseEntity.ok(ent);
			
		}catch(Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(500).body("Oops! Something went wrong");
			//return ResponseEntity.ok(new EmptyObject());
		}
	}

	
	@RequestMapping(path = "/run_event/run_records", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<RunRecord>  getRunProgress(Principal principal){
		
		List<RunRecord> runProgress = runService.getRunRecords(principal.getName());
		return runProgress;
	}
	
//	@RequestMapping(path = "/run_event/records", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
//	public List<RunRecord>  getRunRecords(){
//		
//		List<RunRecord> runProgress = runService.getRecords();
//		return runProgress;
//	}
	
	@RequestMapping(path = "/run_event/records", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<RunRecord>  getRunRecords(){
		
		 
		return runService.getRecords();
	}
	
	@RequestMapping(path = "/run_event/unverified_records", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<RunRecord>  getRunProgress(){
		
		List<RunRecord> runProgress = runService.getUnverifiedRecords();
		return runProgress;
	}
	
	
	@RequestMapping(path = "/run_event/participants", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<RunEventDetail>  getParticipants(){
		
		List<RunEventDetail> participants = runService.getParticipants();
		return participants;
	}
	
	@RequestMapping(path = "/run_event/categories", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<EventCategory>  getCategories(){
		
		List<EventCategory> categories = runService.getCategories();
		return categories;
	}
	
	@RequestMapping(path = "/run_event/run_progress", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getRunProgress(@RequestBody RunEventDetail red){	
		return ResponseEntity.ok(null);
	}
	
	@RequestMapping(path = "/run_event/event_progress/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getAllProgressByEvent(@PathVariable("id") Long id){
		
		List<RunProgress> eventProgress = runService.getAllProgressByEvent(id);
		
		return ResponseEntity.ok(eventProgress);
		
	}
	
	@RequestMapping(path = "/run_event/event_progress_by_facility/{eventId}/{facility}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getAllProgressByEventByFaciltiy(@PathVariable("eventId") Long eventId, @PathVariable("facility")String facility){
		
		List<RunProgress> eventProgress = runService.getAllProgressByEventyByFacility(eventId, facility);
		
		return ResponseEntity.ok(eventProgress);
		
	}
	
	@RequestMapping(path = "/run_event/event_category_progress/{event}/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getProgressByEventByCategory(@PathVariable("event") Long event,@PathVariable("id") Long id){
		
		List<RunProgress> eventProgress = runService.getProgressByEventByCategory(event,id);
		
		return ResponseEntity.ok(eventProgress);
		
	}
	@RequestMapping(path = "/run_event/event_gender_progress/{event}/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getProgressByEventByGender(@PathVariable("event") Long event,@PathVariable("id") Long id){
		
		List<RunProgress> eventProgress = runService.getProgressByGender(event,id);
		
		return ResponseEntity.ok(eventProgress);
		
	}
	@RequestMapping(path = "/run_event/user_progress", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getuserProgress(@RequestBody RunEvent event,Principal principal){	
		
		try {
			
			RunProgress userProgress = runService.getUserProgress(event,principal.getName());
			return ResponseEntity.ok(userProgress);
		
		}catch(EmptyResultDataAccessException e) {
			return ResponseEntity.ok(new EmptyObject());
		}
		
		
	}
	@RequestMapping(path = "/run_event/user_progress_by_id/{eventId}/{userId}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getuserProgress(@PathVariable("eventId")Long eventId, @PathVariable("userId")Long userId){	
		
		try {
			
			RunProgress userProgress = runService.getUserProgressById(eventId, userId);
			return ResponseEntity.ok(userProgress);
		
		}catch(EmptyResultDataAccessException e) {
			return ResponseEntity.ok(new EmptyObject());
		}
		
		
	}
	
	@RequestMapping(path = "/run_event/run_records_by_events_by_user/{eventId}/{userId}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getRunRecordsByEventByUser(@PathVariable("eventId")Long eventId, @PathVariable("userId")Long userId){	

		List<RunRecord> runRecords = runService.getRunRecordsByEventByUser(eventId, userId);
		return ResponseEntity.ok(runRecords);
	}
	
	@RequestMapping(path = "/run_event/approve_record", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> approveRecord(@RequestBody RunRecord record){	
		runService.approveRecord(record);
		
		return ResponseEntity.ok(record);
	}
	
	@RequestMapping(path = "/run_event/disapprove_record", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> disapproveRecord(@RequestBody RunRecord record){	
		runService.disapproveRecord(record);
		return ResponseEntity.ok(record);
	}
	
	@RequestMapping(path = "/current_time", method = RequestMethod.GET)
	public Date getCurrentTime(){	
		
		//ServerTime st = runService.getCurrentDate();
		//System.out.println(st.getCurrentTime());
		Date date = new Date();
		return date;
	}
	
	@RequestMapping(path = "/run_event/addRecord", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> addRecord(MultipartHttpServletRequest request,HttpServletRequest req){	
	
		//runService.addRecord(record,principal.getName());
		String rec = req.getParameter("record");
		
		MultipartFile file = request.getFile("file");
		MultipartFile files = request.getFile("files");
		 RunProgress record = null;
		 
		
		 ObjectMapper mapper = new ObjectMapper();
		 mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
		 mapper.disable(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES);
		 
		 try {

			 record = mapper.readValue(rec, RunProgress.class);
			 runService.addRecord(record,file,files);
			 
			 return ResponseEntity.ok(record);
			
		 } catch (JsonParseException e) {
				e.printStackTrace();
			logger.error(e);
			return ResponseEntity.status(500).body(new ErrorResponse("Error parsing JSON files"));
		} catch (JsonMappingException e) {
			logger.error(e);
			e.printStackTrace();
			return ResponseEntity.status(500).body(new ErrorResponse("Error mapping JSON files"));
		} catch (IOException e) {
			logger.error(e);
			e.printStackTrace();
			return ResponseEntity.status(500).body(new ErrorResponse("IOException"));
		}
		
		 

	}
	@RequestMapping(path = "/uploads/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> Test(@PathVariable("id") Long id) throws IOException {
		
		RunRecord rp = runService.getRunProgress(id);
		Resource file = fileService.loadFile(rp.getFilePath());
		
		return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(file);
	}
	@RequestMapping(path = "/upload/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> Tests(@PathVariable("id") Long id) throws IOException {
		
		RunRecord rp = runService.getRunProgress(id);
		Resource file = fileService.loadFile(rp.getFilePath2());
		
		return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(file);
	}
	
	@RequestMapping(path = "/run_event/run_percent/{eventId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getFacilityRunPercent(@PathVariable("eventId")Long eventId){	

		List<FacilityRunPercent> runPercent = runService.getFacilityRunPercentByEvent(eventId);
		return ResponseEntity.ok(runPercent);
	}
	
	@RequestMapping(path = "/run_event/run_percent/{eventId}/{facility}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getFacilityRunPercent(@PathVariable("eventId")Long eventId, @PathVariable("facility")String facility){	

		FacilityRunPercent runPercent = runService.getFacilityRunPercentByEvent(eventId,facility);
		return ResponseEntity.ok(runPercent);
	}
}
