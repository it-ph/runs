package com.personiv.controller;

import java.security.Principal;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mobile.device.Device;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.personiv.model.ErrorResponse;
import com.personiv.model.Registration;
import com.personiv.model.RunEventDetail;
import com.personiv.model.User;
import com.personiv.model.UserDetailsImpl;
import com.personiv.security.JwtAuthenticationResponse;
import com.personiv.service.CustomUserDetailsService;
import com.personiv.service.RegistrationService;
import com.personiv.service.RunEventService;
import com.personiv.service.UserService;
import com.personiv.utils.JwtTokenUtil;

@RestController
public class AccessController {
	
    private final Log logger = LogFactory.getLog(this.getClass());
    
	@Autowired
	private CustomUserDetailsService userDetailsService;
	
	
	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	
	@RequestMapping("/user-claims")
	public ResponseEntity<?> getPrincipal(Principal principal) {
		try {
			Principal p = principal;
			return ResponseEntity.ok(p);
		}catch(Exception e) {
			return ResponseEntity.status(422).body(new ErrorResponse("Invalid token"));
		}
	}
	
   
	

   @RequestMapping(path = "/authenticate", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody User user, Device device) throws AuthenticationException {
	   	
	   
	 
	   try {
		   
	
	    logger.info("Reading USERNAME:  "+user.getEmail());
	    
        final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        user.getEmail(),
                        user.getPassword()
                )
        );

        logger.info(authentication.getCredentials());
        SecurityContextHolder.getContext().setAuthentication(authentication);
	        
        
	   
        // Reload password post-security so we can generate token
        UserDetails userDetails = userDetailsService.loadUserByUsername(user.getEmail());
        
        UserDetailsImpl userDetailsImpl = (UserDetailsImpl)userDetails;
        
        User u = userDetailsImpl.getUser();
        u.setPassword(null);
        
        String token = jwtTokenUtil.generateToken(userDetails, device);
        
        Date expiration = jwtTokenUtil.getExpirationDateFromToken(token);
        
       
        
        return ResponseEntity.ok(new JwtAuthenticationResponse(token, expiration, "bearer",u));
        
	   }catch(Exception e) {
		   e.printStackTrace();
		   return ResponseEntity.badRequest().body("Oops! Something went wrong.");
	   }
	   
    }
   
   @RequestMapping(value = "/refresh-token", method = RequestMethod.GET)
   public ResponseEntity<?> refreshAndGetAuthenticationToken(HttpServletRequest request) {
       
	   String requestHeader = request.getHeader("Authorization");
	   
	   if (requestHeader != null && requestHeader.startsWith("Bearer ")) {
		   
		   String token = requestHeader.substring(7);
		   
		   if (jwtTokenUtil.canTokenBeRefreshed(token)) {
	           String refreshedToken = jwtTokenUtil.refreshToken(token);

	           Date expiration = jwtTokenUtil.getExpirationDateFromToken(refreshedToken);
	           
	           return ResponseEntity.ok(new JwtAuthenticationResponse(refreshedToken,expiration,"bearer"));
	       } 
		   
		   else return ResponseEntity.badRequest().body(null);
       }
	   else return ResponseEntity.badRequest().body(null);
       
   }
   

   
}
