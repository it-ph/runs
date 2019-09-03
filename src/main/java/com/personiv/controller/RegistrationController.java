package com.personiv.controller;

import java.util.List;

import javax.mail.MessagingException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.personiv.model.ErrorResponse;
import com.personiv.model.PrintableDocument;
import com.personiv.model.Registration;
import com.personiv.model.RunEventDetail;
import com.personiv.service.RegistrationService;
import com.personiv.utils.DocumentBuilder;

@RestController
public class RegistrationController {
	
	private final Log logger = LogFactory.getLog(this.getClass());
	
	@Autowired
	private RegistrationService regService;
	
	@RequestMapping(path = "/registrations", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)	   
	public List<Registration> getRegistrations(){
		return regService.getRegistrations();
	}
	
	
	@RequestMapping(path = "/registrations/approve", method = RequestMethod.POST,consumes  = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)	   
	public ResponseEntity<?> approve(@RequestBody Registration reg) throws MessagingException{
		
		regService.approve(reg);
		return ResponseEntity.ok(reg);
		
	}
	
	@RequestMapping(path = "/registrations/delete", method = RequestMethod.POST,consumes  = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)	   
	public ResponseEntity<?> deleteRegistration(@RequestBody RunEventDetail reg) throws MessagingException{
		
		regService.deleteRegistration(reg);
		return ResponseEntity.ok(reg);
		
	}
	
	@RequestMapping(path = "/registrations/download", method = RequestMethod.POST)	   
	public ResponseEntity<?> downloadRegistrations(){
		
		List<Registration> reg = regService.getRegistrations();
		DocumentBuilder docBuilder = null;
	       
    	docBuilder = new DocumentBuilder();
    	byte bytes[] = new byte[1024];//docBuilder.createTemplate(acc);
    	
    	PrintableDocument document = new PrintableDocument();
    	document.setContent(bytes);
    	document.setContentType("application/octet-stream");
    	
    	return ResponseEntity.ok(document);
	}
	
	@RequestMapping(path = "/registrations/resetPass", method = RequestMethod.POST,consumes  = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)	   
	public ResponseEntity<?> resetPass(@RequestBody Registration reg){
		
		regService.resetPass(reg);
		return ResponseEntity.ok(reg);
		
	}
	
	
	
	@RequestMapping(path = "/register", method = RequestMethod.POST,consumes  = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)	   
	public ResponseEntity<?> register(@RequestBody Registration reg){
		
		if(regService.isRegistered(reg.getEmail())) {
			
			return ResponseEntity.status(500).body(new ErrorResponse("Already registered"));
		
		}else {
			
			regService.register(reg);
			return ResponseEntity.ok(reg);
		}
		
		
		
	}
	

	
	
}
