package com.personiv.dao;

import java.util.List;
import java.util.Random;

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
import com.personiv.model.Role;
import com.personiv.model.User;
import com.personiv.service.MailService;


@Repository
@Transactional(readOnly = false)
public class UserDao extends JdbcDaoSupport{
	
	private JdbcTemplate jdbcTemplate;
	  @Autowired
	    private MailService mailService;
    @Autowired
    private DataSource dataSource;

    @PostConstruct
    private void initialize() {
        setDataSource(dataSource);
        jdbcTemplate = getJdbcTemplate();
    }
   
    @Bean
    BCryptPasswordEncoder passwordEncoder() {
    	return new BCryptPasswordEncoder();
    }
    
	public List<User>getUsers(){
		String query ="SELECT u.id, u.email, u.fullname, u.accountNonLocked,u.accountNonExpired,u.credentialsNonExpired, u.enabled, u.role, u.createdAt, u.updatedAt"
				     + " FROM users u "
				     + "ORDER BY u.id DESC";
		List<User> users =jdbcTemplate.query(query,new BeanPropertyRowMapper<User>(User.class));
		
		
		
		return users;
		
	}
	

	public User getUserByUsername(String username) {
		String sql = "SELECT * FROM users WHERE email =?";
		User user = jdbcTemplate.queryForObject(sql,new Object[] {username}, new BeanPropertyRowMapper<User>(User.class));	

		
		return user;
	}


	public List<Role> getRoles(Integer id) {
		String query ="Call _proc_getUserRoles(?)";
		List<Role> roles =jdbcTemplate.query(query,new Object[] {id},new BeanPropertyRowMapper<Role>(Role.class));
		return roles;
	}


	public void resetPassword(User user) throws MessagingException {
		String query = "UPDATE users SET password = ? WHERE id =?";
	
	
        Random rnd = new Random();
        int value = rnd.nextInt(50); 
        int value2 = rnd.nextInt(50); 
        
		String pass = "!Welcome"+value+""+value2;
		String message ="Your temporary password is " +pass;
		jdbcTemplate.update(query,new Object[] {passwordEncoder().encode(pass),user.getId()});
		
	
		
		mailService.sendMail(user.getEmail(), "Run Proud Password Reset", message,"runproud@personiv.com");
		
		
		
	}
	



	public void disableUser(User user) {
		String query = "UPDATE users SET enabled = 0 WHERE id =?";
		jdbcTemplate.update(query, new Object[] {user.getId()});
	}
	
	public void enableUser(User user) {
		String query = "UPDATE users SET enabled = 1 WHERE id =?";
		jdbcTemplate.update(query, new Object[] {user.getId()});
		
	}
	
	public String testPassword() {
		return passwordEncoder().encode("!Welcome18");
	}

	public void changePassword(User user) {
		String sql ="UPDATE users SET password =? WHERE email =?";
		jdbcTemplate.update(sql,new Object[] {
				passwordEncoder().encode(user.getPassword()),
				user.getEmail()
		});
	}

	public void deleteUser(User user) {
		String query2 ="DELETE FROM notifications WHERE recipient =?";
		String query3 ="DELETE FROM registrations WHERE email =?";
		String query = "DELETE FROM users WHERE id = ?";

		jdbcTemplate.update(query2, new Object[] {user.getId()});
		jdbcTemplate.update(query3, new Object[] {user.getEmail()});
		jdbcTemplate.update(query, new Object[] {user.getId()});
	}

	public void deleteUserRegistration(Registration reg) {
		String sql ="DELETE FROM registrations WHERE id =?";
		jdbcTemplate.update(sql, new Object[] {reg.getId()});
		
	}




	
}
