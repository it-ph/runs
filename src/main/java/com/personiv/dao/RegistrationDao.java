package com.personiv.dao;

import java.util.List;


import javax.annotation.PostConstruct;
import javax.mail.MessagingException;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.personiv.model.Registration;
import com.personiv.model.RunEventDetail;
import com.personiv.model.User;
import com.personiv.service.MailService;

@Repository
@Transactional(readOnly = false)
public class RegistrationDao extends JdbcDaoSupport{
	
	private JdbcTemplate jdbcTemplate;

    @Autowired
    private DataSource dataSource;
    
    @Autowired
    private MailService mailService;
    
    @PostConstruct
    private void initialize() {
        setDataSource(dataSource);
        jdbcTemplate = getJdbcTemplate();
    }
   
    @Bean
    BCryptPasswordEncoder passwordEncoder() {
    	return new BCryptPasswordEncoder();
    }
    
    public List<Registration> getRegistrations(){
    	String query ="SELECT r.id,r.email,r.fullname,r.gender,r.reg_status approved,facility, r.createdAt,r.updatedAt "
    			    + "  FROM registrations r "
    			    + " ORDER BY r.id DESC";
    			
    	return jdbcTemplate.query(query,new BeanPropertyRowMapper<Registration>(Registration.class));
    }

	public void approve(Registration reg) throws MessagingException {
		String query ="UPDATE registrations SET reg_status = 1, updatedAt = CURRENT_TIMESTAMP WHERE id =?";
		String query2 ="INSERT INTO users(email,fullname,gender,password)"
				     + "SELECT r.email,r.fullname,r.gender,r.password "
				     + "  FROM registrations r"
				     + " WHERE r.id =?";
		
		jdbcTemplate.update(query,new Object[] {reg.getId()});
		jdbcTemplate.update(query2,new Object[] {reg.getId()});
		
		String message ="Your registration for runproud.personiv.com has been approved\n\n"+
						"full name: "+reg.getFullname()+"\n"+
				        "username: "+reg.getEmail()+"\n"+
        				"site: "+reg.getFacility()+"\n";
		
		
		mailService.sendMail(reg.getEmail(), "Run Proud Registration", message,"runproud@personiv.com");
	}

	public void register(Registration reg) {
		String query ="INSERT INTO registrations(email,fullname,gender,password,facility) VALUES(?,?,?,?,?)";
		
		jdbcTemplate.update(query,new Object[] {
				reg.getEmail(),
				reg.getFullname(),
				reg.getGender(),
				passwordEncoder().encode(reg.getPassword()),
				reg.getFacility()
		});
	}

	public Boolean isRegistered(String username) {
		String sql ="SELECT u.*" + 
				   "  FROM users u" + 
				   "  LEFT JOIN registrations r on u.email = r.email" + 
				   " WHERE r.email = ? OR u.email = ?";
		List<User> user = jdbcTemplate.query(sql,new Object[] {username,username} ,new BeanPropertyRowMapper<User>(User.class));
	
		return user.size() > 0;
	}

	public void resetPass(Registration reg) {
		String sql= "UPDATE users SET password =? WHERE email =?";
		jdbcTemplate.update(sql, new Object[] {
				passwordEncoder().encode("!Welcome2018"),
				reg.getEmail()
		});
		
	}

//	public void deleteRegistration(RunEventDetail reg) {
//		
//		String sql= "DELETE FROM run_event_reg WHERE event_id = ? AND categ_id = ? AND user_id = ?";
//		
//		jdbcTemplate.update(sql, new Object[] {
//				reg.getRunEvent().getId(),
//				reg.getEventCateg().getId(),
//				reg.getUser().getId()
//		});
//	}
	
	public void deleteRegistration(RunEventDetail reg) {
		String sql= "DELETE FROM run_event_reg where event_id= ? AND categ_id = ? AND user_id =?";
		
		jdbcTemplate.update(sql, new Object[] {
				reg.getRunEvent().getId(),
				reg.getEventCateg().getId(),
				reg.getUser().getId()
		});
	}

}
