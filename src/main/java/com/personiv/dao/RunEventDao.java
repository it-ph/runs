package com.personiv.dao;

import java.io.IOException;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.mail.MessagingException;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.multipart.MultipartFile;

import com.personiv.model.Entitlement;
import com.personiv.model.EventCategory;
import com.personiv.model.EventRegistryStatus;
import com.personiv.model.FacilityRunPercent;
import com.personiv.model.RunEvent;
import com.personiv.model.RunEventCategory;
import com.personiv.model.RunEventDetail;
import com.personiv.model.RunProgress;
import com.personiv.model.RunRecord;
import com.personiv.model.User;
import com.personiv.model.UserLoc;
import com.personiv.service.FileService;
import com.personiv.service.MailService;
import com.personiv.utils.ParticipantsRowMapper;
import com.personiv.utils.RunEventCategoryRowMapper;
import com.personiv.utils.RunEventDetailRowMapper;
import com.personiv.utils.RunProgressRowMapper;
import com.personiv.utils.RunRecordRowMapper;


@Repository
@Transactional(readOnly = false)
public class RunEventDao extends JdbcDaoSupport{
	
	private JdbcTemplate jdbcTemplate;

    @Autowired
    private DataSource dataSource;
    
    @Autowired
    private MailService mailService;
    
    @Autowired
    FileService fileService;
    
    @PostConstruct
    private void initialize() {
        setDataSource(dataSource);
        jdbcTemplate = getJdbcTemplate();
    }
    

    /*An active Run Event in which the (name) is registered */
	public RunEventDetail getActiveReg(String name) {
		String sql ="SELECT re.id eventId, re.reg_start regStart, re.reg_end regEnd, re.run_start runStart, re.run_end runEnd,re.createdAt eventCreated, re.updatedAt eventUpdated, " + 
				"		    ec.id categId, ec.distance, ec.category, ec.createdAt categCreated, ec.updatedAt categUpdated, "+
				"	        u.id userId, u.email,u.fullname,u.createdAt userCreated, u.updatedAt userUpdated, " + 
				"           reu.createdAt reCreated, reu.updatedAt reUpdated,reu.approved, reu.shirt_size shirtSize,reu.forhim Hima"+
//				"           en.id entId, en.name entName, en.cost entCost, en.createdAt entCreated, en.updatedAt entUpdated "+
				"      FROM run_events re " + 
				"      JOIN run_event_reg reu  on reu.event_id = re.id " + 
				"      JOIN users u on reu.user_id = u.id " + 
//				"      JOIN entitlements en on reu.ent_id = en.id "+
				"      JOIN event_categories ec on reu.categ_id = ec.id " + 
				"     WHERE ((CURDATE()  >= re.reg_start AND CURDATE() <= re.reg_end) OR (CURDATE()  >= re.run_start AND CURDATE() <= re.run_end))AND " +
				"           u.email =?"+
				"     ORDER BY reu.createdAt DESC LIMIT 1";
		return jdbcTemplate.queryForObject(sql, new Object[] {name},new RunEventDetailRowMapper());
	}
	
	public RunEvent getCurrentRun() {
		String sql ="SELECT re.id, re.reg_start regStart, re.reg_end regEnd, re.run_start runStart, re.run_end runEnd, re.createdAt, re.updatedAt "+
				    "  FROM run_events re ";
				    //" WHERE (CURDATE()  >= re.reg_start AND CURDATE() <= re.reg_end) OR (CURDATE()  >= re.run_start AND CURDATE() <= re.run_end) LIMIT 1";
		return jdbcTemplate.queryForObject(sql,new BeanPropertyRowMapper<RunEvent>(RunEvent.class));
	}
    
	public List<RunEventCategory> getRunEventRegistry(){
		String sql ="SELECT re.id eventId, re.reg_start, re.reg_end, re.run_start, re.run_end,re.createdAt runCreated, re.updatedAt runUpdated," + 
				"		    ec.id categId, ec.distance, ec.category, ec.createdAt categCreated, ec.updatedAt categUpdated, " +
				"           reu.createdAt reCreated, reu.updatedAt reUpdated,reu.approved "+
				"      FROM run_event_users reu " + 
				"      JOIN run_events re on reu.event_id = re.id " + 
				"      JOIN users u on reu.user_id = u.id " + 
				"      JOIN event_categories ec on reu.categ_id = ec.id";
		return jdbcTemplate.query(sql, new RunEventCategoryRowMapper());
		
	}
	
    /*An active Run Event in which the (name) is registered */
	public RunEventDetail getActiveRun2(String name) {
		String sql ="SELECT ru.id eventId,ru.reg_start regStart, ru.reg_end regEnd, ru.run_start runStart, ru.run_end runEnd, ru.createdAt eventCreated, ru.updatedAt eventUpdated, " + 
		 		"	    u.id userId, u.email,u.fullname,u.createdAt userCreated, u.updatedAt userUpdated, " + 
		 		"	    ec.id categId, ec.category, ec.distance, ec.createdAt categCreated, ec.updatedAt categUpdated" + 
		 		"  FROM run_event_users reu " + 
		 		"  JOIN users u on reu.user_id = u.id " + 
		 		"  JOIN run_events ru on reu.event_id = ru.id " + 
		 		"  JOIN event_categories ec on  reu.categ_id =ec.id "+
				"     WHERE (CURDATE()  >= ru.reg_start AND CURDATE() <= ru.reg_end) AND  " + 
				" 		    u.email = ? " + 
				"     ORDER BY reu.createdAt DESC LIMIT 1";
		
		RunEventDetail ru = jdbcTemplate.queryForObject(sql,new Object[] {name}, new ParticipantsRowMapper());
		
		return ru;
	}
	
	public List<RunRecord> getRunRecords(String username){
		
		String sql2 ="SELECT r.id, r.file_path,r.file_name,r.file_path2, r.file_name2, r.run_time time, r.run_distance distance,r.createdAt,r.updatedAt,r.verified,r.categ categ,r.gallery gallery,"+
				"		( " +
				"		  SELECT  category " + 
				"		  FROM event_categories rp " + 
				"		  JOIN run_event_reg u1 on u1.categ_id = rp.id " + 
				"		  JOIN users u11 on u11.id = u1.user_id " +
				"		  JOIN registrations reg on reg.fullname = u11.fullname " + 
				"		 WHERE rp.id = u1.categ_id AND reg.email=u.email limit 1" + 
				"		 ) as Facility" + 
		             "  FROM run_progress r"+
				     "  JOIN users u on r.user_id = u.id "+
	  	             " WHERE u.email = ?"+
				     " ORDER BY r.id DESC";
		
		List<RunRecord> runRecords =jdbcTemplate.query(sql2
					,new Object[]{username} 
					,new BeanPropertyRowMapper<RunRecord>(RunRecord.class));
		
		return runRecords;
	}

	public List<RunEventDetail> getParticipants() {
		
		String sql ="SELECT ru.id eventId,ru.reg_start regStart, ru.reg_end regEnd, ru.run_start runStart, ru.run_end runEnd, ru.createdAt eventCreated, ru.updatedAt eventUpdated, " + 
		 		"	    u.id userId, u.email,u.fullname,u.createdAt userCreated, u.updatedAt userUpdated, " + 
		 		"	    ec.id categId, ec.category, ec.distance, ec.createdAt categCreated, ec.updatedAt categUpdated, " + 
				"       reu.createdAt reCreated, reu.updatedAt reUpdated,reu.approved,reu.shirt_size shirtSize,reu.forhim Hima"+
//		 		"       en.id entId,en.name entName, en.cost entCost, en.createdAt entCreated, en.updatedAt entUpdated "+
		 		"  FROM run_event_reg reu " + 
		 		"  JOIN users u on reu.user_id = u.id " + 
//		 		"  JOIN entitlements en on reu.ent_id = en.id "+
		 		"  JOIN run_events ru on reu.event_id = ru.id " + 
		 		"  JOIN event_categories ec on  reu.categ_id =ec.id "+
				" WHERE (CURDATE()  >= ru.reg_start AND CURDATE() <= ru.reg_end) OR (CURDATE()  >= ru.run_start AND CURDATE() <= ru.run_end)"
				+ "ORDER BY reu.createdAt DESC";
		
		List<RunEventDetail> ru = jdbcTemplate.query(sql, new RunEventDetailRowMapper());
		 
		
		 return ru;
	}
	



	public List<Entitlement> getEntitlements(String name) {
		String sql ="SELECT r.facility location" + 
				    "  FROM registrations r" + 
				    "  JOIN users u on r.email = u.email "+
				    " WHERE u.email = ?";
		
		
		UserLoc loc = jdbcTemplate.queryForObject(sql,new Object[] {name}, new BeanPropertyRowMapper<UserLoc>(UserLoc.class));
		
		
		
		String query =(loc.getLocation().equals("MANILA") ||loc.getLocation().equals("AUSTIN")) ? "SELECT * FROM entitlements WHERE id = 2": "SELECT * from entitlements";
		
		return jdbcTemplate.query(query, new BeanPropertyRowMapper<Entitlement>(Entitlement.class));
	}


	public List<EventCategory> getCategories() {

		String sql ="SELECT c.id,c.category,c.categ_desc description,c.distance, c.createdAt, c.updatedAt" + 
				    "  FROM event_categories c limit 3";
		return jdbcTemplate.query(sql, new BeanPropertyRowMapper<EventCategory>(EventCategory.class));

	}
	
	

	public void registerRun(RunEventDetail runEventDetail) {
		String sql ="INSERT INTO run_event_reg(event_id, categ_id, user_id,ent_id, shirt_size) VALUES(?,?,?,?,?)";
		jdbcTemplate.update(sql, new Object[] {
				runEventDetail.getRunEvent().getId(),
				runEventDetail.getEventCateg().getId(),
				runEventDetail.getUser().getId(),
				runEventDetail.getEntitlement().getId(),
				runEventDetail.getShirtSize()
		});
		
		String sql2 ="INSERT INTO notifications(recipient,title,body,click_action,createdAt)" + 
				"SELECT u.id,?,?,?,CURRENT_TIMESTAMP " + 
				"  FROM users u" + 
				" WHERE u.role ='ADMIN'";
		
		jdbcTemplate.update(sql2, new Object[] {
				runEventDetail.getUser().getFullname(),
				runEventDetail.getEventCateg().getCategory()+" registration",
				"run_registrations"
		});
		
	}


	public void approveRegistration(RunEventDetail runEventDetail) {
		
		try {
			
			String sql ="UPDATE run_event_reg SET approved =1 WHERE event_id =? AND categ_id =? AND user_id =?";
			jdbcTemplate.update(sql, new Object[] {
					runEventDetail.getRunEvent().getId(),
					runEventDetail.getEventCateg().getId(),
					runEventDetail.getUser().getId()
			});
			
			SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
			
			String message="Your registration for run event has been approved"+
			               "\n\nRun event: "+dt.format(runEventDetail.getRunEvent().getRunStart())+" - "+dt.format(runEventDetail.getRunEvent().getRunEnd())+
			               "\nRun category: "+runEventDetail.getEventCateg().getCategory();
			               
//			               "\nEntitlement: "+runEventDetail.getEntitlement().getName();
			
			mailService.sendMail(runEventDetail.getUser().getEmail(), "Run Event Registration", message, "runproud@personiv.com");
			
			String sql2 ="INSERT INTO notifications(recipient,title,body,click_action,createdAt)" + 
					"VALUES(?,?,?,?,CURRENT_TIMESTAMP)";
			jdbcTemplate.update(sql2, new Object[] {
					runEventDetail.getUser().getId(),
					"Registration request",
					"Your registration for "+runEventDetail.getEventCateg().getCategory() +" has been approved",
					"home"
			});
		} catch (MessagingException e) {}
	}


	public RunProgress getUserProgress(RunEvent event,String name) {
		String sql =" SELECT u.id userId, u.email, u.fullname userFullname,(ROUND(((SUM(TIME_TO_SEC(rp.run_time)) / sum(run_distance)) / 60) , 2)) pace, " + 
			       "         re.id eventId, re.reg_start eventRegStart, re.reg_end eventRegEnd, re.run_start eventRunStart, re.run_end eventRunEnd, " + 
			       " 		 rp.user_id,rp.event_id,TIME_FORMAT(SEC_TO_TIME(SUM(TIME_TO_SEC(rp.run_time))), '%H:%i:%s') totalElapseTime, sum(run_distance) distanceTraveled, rp.createdAt rpCreated,rp.gallery As gallery," + 
			       "         ec.id categId, ec.category categName, ec.distance categDistance,ec.categ_desc categDesc "+
			       "    FROM run_progress rp " + 
				   "    JOIN run_events re ON re.id = rp.event_id " + 
				   "    JOIN users u ON rp.user_id = u.id " + 
				   "    JOIN event_categories ec on rp.categ_id = ec.id "+
 	               "   WHERE re.id = ?" + 
 	               "		 AND u.email = ? " + 
 	               "	     AND rp.verified = 'VERIFIED'" + 
 	               "   GROUP BY re.id,u.id,ec.id";
		
		return jdbcTemplate.queryForObject(sql,
				new Object[] {
						event.getId(),
						name
				},new RunProgressRowMapper());
	}
	
	public RunProgress getUserProgressById(Long eventId, Long userId) {
		String sql =" SELECT u.id userId, u.email, u.fullname userFullname,(ROUND(((SUM(TIME_TO_SEC(rp.run_time)) / sum(run_distance)) / 60) , 2)) pace, " + 
			       "         re.id eventId, re.reg_start eventRegStart, re.reg_end eventRegEnd, re.run_start eventRunStart, re.run_end eventRunEnd, " + 
			       " 		 rp.user_id,rp.event_id,TIME_FORMAT(SEC_TO_TIME(SUM(TIME_TO_SEC(rp.run_time))), '%H:%i:%s') totalElapseTime, sum(run_distance) distanceTraveled, rp.createdAt rpCreated,rp.gallery gallery," + 
			       "         ec.id categId, ec.category categName, ec.distance categDistance,ec.categ_desc categDesc "+
			       "    FROM run_progress rp " + 
				   "    JOIN run_events re ON re.id = rp.event_id " + 
				   "    JOIN users u ON rp.user_id = u.id " + 
				   "    JOIN event_categories ec on rp.categ_id = ec.id "+
	               "   WHERE re.id = ? " + 
	               "		 AND u.id = ? " + 
	               "	     AND rp.verified = 'VERIFIED'" + 
	               "   GROUP BY re.id,u.id,ec.id";
		
		return jdbcTemplate.queryForObject(sql, new Object[] {eventId,userId}, new RunProgressRowMapper());
	}
	
	
	
	public List<RunProgress> getProgressByCategory(Long eventId,Long categoryId){
		
		String sql =" SELECT u.id userId, u.email, u.fullname userFullname, " + 
			       "         re.id eventId, re.reg_start eventRegStart, re.reg_end eventRegEnd, re.run_start eventRunStart, re.run_end eventRunEnd, " + 
			       " 		 rp.user_id,rp.event_id,rp.gallery gallery,TIME_FORMAT(SEC_TO_TIME(SUM(TIME_TO_SEC(rp.run_time))), '%H:%i:%s') totalElapseTime, sum(run_distance) distanceTraveled, " + 
			       "         ec.id categId, ec.category categName, ec.distance categDistance,ec.categ_desc categDesc "+
			       "    FROM run_progress rp " + 
				   "    JOIN run_events re ON re.id = rp.event_id " + 
				   "    JOIN users u ON rp.user_id = u.id " + 
				   "    JOIN event_categories ec on rp.categ_id = ec.id "+
	               "   WHERE re.id = ? " + 
	               "	     AND rp.verified = 'VERIFIED'"
	               + "       AND ec.id = ?" + 
	               "   GROUP BY re.id,u.id,ec.id"
	               + " ORDER BY sum(run_distance) DESC, SUM(TIME_TO_SEC(rp.run_time)) ASC";
		
		return jdbcTemplate.query(sql,
				new Object[] {
						eventId,categoryId
				},
				new RunProgressRowMapper());
	}
public List<RunProgress> getProgressByGender(Long eventId,Long categoryId){
		
		String sql =" SELECT u.id userId, u.email, u.fullname userFullname, " + 
			       "         re.id eventId, re.reg_start eventRegStart, re.reg_end eventRegEnd, re.run_start eventRunStart, re.run_end eventRunEnd, " + 
			       " 		 rp.user_id,rp.event_id,TIME_FORMAT(SEC_TO_TIME(SUM(TIME_TO_SEC(rp.run_time))), '%H:%i:%s') totalElapseTime, sum(run_distance) distanceTraveled, " + 
			       "         ec.id categId, ec.category categName, ec.distance categDistance,ec.categ_desc categDesc "+
			       "    FROM run_progress rp " + 
				   "    JOIN run_events re ON re.id = rp.event_id " + 
				   "    JOIN users u ON rp.user_id = u.id " + 
				   "    JOIN event_categories ec on rp.categ_id = ec.id "+
	               "   WHERE re.id = ? " + 
	               "	     AND rp.verified = 'VERIFIED'"
	               + "       AND ec.id = ?" + 
	               "       AND u.gender = ?" + 
	               "   GROUP BY re.id,u.id,ec.id"
	               + " ORDER BY sum(run_distance) DESC, SUM(TIME_TO_SEC(rp.run_time)) ASC";
		
		return jdbcTemplate.query(sql,
				new Object[] {
						eventId,categoryId
				},
				new RunProgressRowMapper());
	}
	public List<RunProgress> getAllProgressByEvent(Long eventId){
		
		String sql =" SELECT u.id userId, u.email, u.fullname userFullname,(ROUND(((SUM(TIME_TO_SEC(rp.run_time)) / sum(run_distance)) / 60) , 2)) pace, " + 
			       "         re.id eventId, re.reg_start eventRegStart, re.reg_end eventRegEnd, re.run_start eventRunStart, re.run_end eventRunEnd, " + 
			       " 		 rp.user_id,rp.event_id,TIME_FORMAT(SEC_TO_TIME(SUM(TIME_TO_SEC(rp.run_time))), '%H:%i:%s') totalElapseTime, ROUND(sum(run_distance),2) distanceTraveled, rp.createdAt rpCreated,rp.gallery gallery," + 
			       "         ec.id categId, ec.category categName, ec.distance categDistance,ec.categ_desc categDesc "+
			       "    FROM run_progress rp " + 
				   "    JOIN run_events re ON re.id = rp.event_id " + 
				   "    JOIN users u ON rp.user_id = u.id " + 
				   "    JOIN event_categories ec on rp.categ_id = ec.id "+
	               "   WHERE re.id = ? "  + 
	               "	     AND rp.verified = 'VERIFIED'" + 
	               "   GROUP BY re.id,u.id,ec.id"
	             //  + " ORDER BY ((SUM(TIME_TO_SEC(rp.run_time)) / sum(run_distance)) / 60)";
	             + " ORDER BY  sum(run_distance) DESC";
		
		return jdbcTemplate.query(sql,new Object[] {eventId},new RunProgressRowMapper());
	}
	
	public List<RunProgress> getAllProgressByEventByFacility(Long eventId, String facility){
		String sql =" SELECT u.id userId, u.email, u.fullname userFullname,(ROUND(((SUM(TIME_TO_SEC(rp.run_time)) / sum(run_distance)) / 60) , 2)) pace, " + 
			       "         re.id eventId, re.reg_start eventRegStart, re.reg_end eventRegEnd, re.run_start eventRunStart, re.run_end eventRunEnd, " + 
			       " 		 rp.user_id,rp.event_id,TIME_FORMAT(SEC_TO_TIME(SUM(TIME_TO_SEC(rp.run_time))), '%H:%i:%s') totalElapseTime, ROUND(sum(run_distance),2) distanceTraveled, rp.createdAt rpCreated," + 
			       "         ec.id categId, ec.category categName, ec.distance categDistance,ec.categ_desc categDesc "+
			       "    FROM run_progress rp " + 
				   "    JOIN run_events re ON re.id = rp.event_id " + 
				   "    JOIN users u ON rp.user_id = u.id " + 
				   "    JOIN event_categories ec on rp.categ_id = ec.id "+
				   "    JOIN registrations r on r.email = u.email "+
	               "   WHERE re.id = ? AND r.facility =? "  + 
	               "	     AND rp.verified = 'VERIFIED'" + 
	               "   GROUP BY re.id,u.id,ec.id"
	               + " ORDER BY sum(run_distance) DESC ";
		
		return jdbcTemplate.query(sql,new Object[] {eventId, facility},new RunProgressRowMapper());
	
	}
	
	public void addRecord(RunProgress record, MultipartFile file, MultipartFile files) throws IOException {
		String sql ="INSERT INTO run_progress (user_id, event_id,categ_id,file_path, file_name,file_path2, file_name2,run_time,run_distance,categ,gallery) VALUES(?,?,?,?,?,?,?,?,?,?,?)";
		
		String path ="/opt/tomcat/run_upload/"+record.getUser().getFullname()+"/records";
		String path2 ="/opt/tomcat/run_uploads/"+record.getUser().getFullname()+"/records";
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss_");
	
		String filename  =dateFormat.format(new Date())+file.getOriginalFilename();
		String filename2  =dateFormat.format(new Date())+files.getOriginalFilename();
	
		jdbcTemplate.update(sql,new Object[] {
				record.getUser().getId(),
				record.getEvent().getId(),
				record.getEventCategory().getId(),
				path+"/"+filename,
				filename,
				path2+"/"+filename2,
				filename2,
				record.getTotalElapseTime(),
				record.getDistanceTraveled(),
				record.getCateg(),
				record.getGallery()
				
		});
		fileService.uploadFile(file, path,filename);
		fileService.uploadFile(files, path2,filename2);
		
		String sql2 ="INSERT INTO notifications(recipient,title,body,click_action,createdAt)" + 
				"SELECT u.id,?,?,?,CURRENT_TIMESTAMP " + 
				"  FROM users u" + 
				" WHERE u.role ='ADMIN'";
		
		jdbcTemplate.update(sql2, new Object[] {
				"Record Submition",
				record.getUser().getFullname()+" has submitted a record",
				"records"
		});
		
	}


	public RunRecord getRunProgress(Long id) {
		String sql ="SELECT r.id, r.file_path,r.file_name,r.file_path2, r.file_name2, r.run_time time, r.run_distance distance,r.createdAt,r.updatedAt,r.verified,r.categ,r.gallery"+
	                 "  FROM run_progress r"+
	                 " WHERE r.id =?";
		
		return jdbcTemplate.queryForObject(sql,new Object[] {id}, new BeanPropertyRowMapper<RunRecord>(RunRecord.class));
	}


	public List<RunRecord> getUnverifiedRecords() {
		String sql ="SELECT r.id, r.file_path,r.file_name,r.file_path2, r.file_name2 r.run_time time, r.run_distance distance,r.createdAt,r.updatedAt,r.verified,r.categ"+
	             "  FROM run_progress r"+
			     "  JOIN users u on r.user_id = u.id "+
 	             " WHERE r.verified ='NO'  "+
			     " ORDER BY r.id DESC";
	
		List<RunRecord> runRecords =jdbcTemplate.query(sql,new BeanPropertyRowMapper<RunRecord>(RunRecord.class));
		
		return runRecords;
	}


	public void approveRecord(RunRecord record) {
		String sql ="UPDATE run_progress SET verified = 'VERIFIED' WHERE id =?";
		jdbcTemplate.update(sql,new Object[] {
			record.getId()	
		});
		
		String sql2 ="INSERT INTO notifications(recipient,title,body,click_action,createdAt)" + 
				"VALUES(?,?,?,?,CURRENT_TIMESTAMP)";
		
		jdbcTemplate.update(sql2, new Object[] {
				record.getUser().getId(),
				"Record submission",
				"Your record submission has been approved",
				"home"
		});
	}
	public void disapproveRecord(RunRecord record) {
		
		String sql ="UPDATE run_progress SET verified = 'INVALID' WHERE id =?";
		jdbcTemplate.update(sql,new Object[] {
			record.getId()	
		});
		
		String sql2 ="INSERT INTO notifications(recipient,title,body,click_action,createdAt)" + 
				"VALUES(?,?,?,?,CURRENT_TIMESTAMP)";
		jdbcTemplate.update(sql2, new Object[] {
				record.getUser().getId(),
				"Record submission",
				"Your record submission has been disapproved",
				"home"
		});
	}

	public List<RunProgress> getAllRunProgress(){
		String sql =" SELECT u.id userId, u.email, u.fullname userFullname, " + 
			       "         re.id eventId, re.reg_start eventRegStart, re.reg_end eventRegEnd, re.run_start eventRunStart, re.run_end eventRunEnd, " + 
			       " 		 rp.user_id,rp.event_id,rp.run_time totalElapseTime, rp.run_distance distanceTraveled, rp.createdAt rpCreated,rp.gallery gallery," + 
			       "         ec.id categId, ec.category categName, ec.distance categDistance,ec.categ_desc categDesc "+
			       "    FROM run_progress rp " + 
				   "    JOIN run_events re ON re.id = rp.event_id " + 
				   "    JOIN users u ON rp.user_id = u.id " + 
				   "    JOIN event_categories ec on rp.categ_id = ec.id "
				   + "  ORDER BY rp.id DESC";
		
		return jdbcTemplate.query(sql,new RunProgressRowMapper());
	}
	
	public List<RunRecord> getRecords() {
		String sql =" SELECT u.id userId, u.email, u.fullname userFullname, " + 
			       "         re.id eventId, re.reg_start eventRegStart, re.reg_end eventRegEnd, re.run_start eventRunStart, re.run_end eventRunEnd, " + 
			       " 		 rp.user_id,rp.event_id,rp.run_time totalElapseTime, rp.run_distance distanceTraveled, rp.verified rpVerified, rp.id, rp.categ categ, rp.file_name, rp.file_path, rp.createdAt rpCreatedAt,rp.gallery rpGallery," + 
			       "         ec.id categId, ec.category categName, ec.distance categDistance,ec.categ_desc categDesc "+
			       "    FROM run_progress rp " + 
				   "    JOIN run_events re ON re.id = rp.event_id " + 
				   "    JOIN users u ON rp.user_id = u.id " + 
				   "    JOIN event_categories ec on rp.categ_id = ec.id "
				   + "  ORDER BY rp.id DESC";
		List<RunRecord> runRecords =jdbcTemplate.query(sql,new RunRecordRowMapper());
		
		return runRecords;
	}

	
	public List<RunProgress> getAllProgressByFacility(String facility){
		String sql ="SELECT u.id userId, u.email, u.fullname userFullname,((SUM(TIME_TO_SEC(rp.run_time)) / sum(run_distance)) / 60) pace,     " + 
					"       re.id eventId, re.reg_start eventRegStart, re.reg_end eventRegEnd, re.run_start eventRunStart, re.run_end eventRunEnd,     " + 
					"		 rp.user_id,rp.event_id,TIME_FORMAT(SEC_TO_TIME(SUM(TIME_TO_SEC(rp.run_time))), '%H:%i:%s') totalElapseTime, sum(run_distance) distanceTraveled, rp.createdAt rpCreated,    " + 
					"       ec.id categId, ec.category categName, ec.distance categDistance,ec.categ_desc categDesc," + 
					"		 r.facility,r.id" + 
					"  FROM run_progress rp     " + 
					"  JOIN run_events re ON re.id = rp.event_id     " + 
					"  JOIN users u ON rp.user_id = u.id     " + 
					"  JOIN event_categories ec on rp.categ_id = ec.id   " + 
					"  JOIN registrations r on u.email = r.email" + 
					"  " + 
					" WHERE re.id = 1  AND r.facility = ?" + 
					" 	     AND rp.verified = 'VERIFIED'    " + 
					" GROUP BY re.id,u.id,ec.id";
		
		return jdbcTemplate.query(sql,new Object[] {facility},new RunProgressRowMapper());
	}
	public List<RunProgress> getProgressByEventByCategory(Long event, Long id) {
		String sql =" SELECT u.id userId, u.email, u.fullname userFullname, " + 
			       "         re.id eventId, re.reg_start eventRegStart, re.reg_end eventRegEnd, re.run_start eventRunStart, re.run_end eventRunEnd, ROUND ( ((SUM(TIME_TO_SEC(rp.run_time)) / sum(run_distance)) / 60) , 2)pace, " + 
			       " 		 rp.user_id,rp.event_id,TIME_FORMAT(SEC_TO_TIME(SUM(TIME_TO_SEC(rp.run_time))), '%H:%i:%s') totalElapseTime, ROUND(sum(rp.run_distance),2) distanceTraveled, rp.createdAt rpCreated," + 
			       "         ec.id categId, ec.category categName, ec.distance categDistance,ec.categ_desc categDesc "+
			       "    FROM run_progress rp " + 
				   "    JOIN run_events re ON re.id = rp.event_id " + 
				   "    JOIN users u ON rp.user_id = u.id " + 
				   "    JOIN event_categories ec on rp.categ_id = ec.id "+
	               "   WHERE re.id = ? "  + 
	               "	     AND rp.verified = 'VERIFIED' AND ec.id =?" + 
	               "   GROUP BY re.id,u.id,ec.id"
	            // + "   ORDER BY sum(run_distance) DESC, SUM(TIME_TO_SEC(rp.run_time)) DESC";
	            	+"  ORDER BY sum(rp.run_distance) DESC";
		
		return jdbcTemplate.query(sql,new Object[] {event,id},new RunProgressRowMapper());
	}


	public List<RunRecord> getRunRecordsByEventByUser(Long eventId, Long userId) {
		String sql2 ="SELECT r.id, r.file_path,r.file_name,r.file_path2, r.file_name2 r.run_time time, r.run_distance distance,r.createdAt,r.updatedAt,r.verified,r.categ,r.gallery"+
	             "  FROM run_progress r"+
			     "  JOIN users u on r.user_id = u.id "+
 	             " WHERE u.id =? AND r.event_id =? "+
			     " ORDER BY r.id DESC";
	
		
		List<RunRecord> runRecords =jdbcTemplate.query(sql2
					,new Object[]{ userId, eventId} 
					,new BeanPropertyRowMapper<RunRecord>(RunRecord.class));
		
		return runRecords;
	}

	public List<RunRecord> getRunRecords() {
		String sql2 ="SELECT r.id, r.file_path,r.file_name,r.file_path2, r.file_name2, r.run_time time, r.run_distance distance,r.createdAt,r.updatedAt,r.verified,r.categ,u.fullname As full,u.id As id"+
	             "  FROM run_progress r"+
			     "  JOIN users u on r.user_id = u.id "+
			     " ORDER BY r.id DESC";
	
		
		List<RunRecord> runRecords =jdbcTemplate.query(sql2,new BeanPropertyRowMapper<RunRecord>(RunRecord.class));
		
		return runRecords;
	}


	public List<EventRegistryStatus> getEventRegistryStatusByEventByFacility(Long event, String facility) {
		String sql ="SELECT u.fullname 'fullName',  " + 
					"		 r.facility , " + 
					"		 re.id 'eventId', " + 
					"		 re.reg_start 'eventRegStart', " + 
					"		 re.reg_end 'eventRegEnd', " + 
					"		 re.run_start 'eventRunStart', " + 
					"		 re.run_end 'eventRunEnd', " + 
					"		 u.email,  " + 
					"		 COALESCE(ec.category,'NOT REGISTERED') 'eventCategory',  " + 
					"		 COALESCE(reg.shirt_size,'N/A') 'shirtSize',reg.createdAt 'eventRegistered' " + 
					"  FROM users u " + 
					"  JOIN registrations r ON r.email = u.email  " + 
					"  LEFT JOIN run_event_reg reg ON reg.user_id = u.id " + 
					"  LEFT JOIN run_events re ON re.id = reg.event_id " + 
					"  LEFT JOIN event_categories ec ON reg.categ_id = ec.id " + 
					" WHERE (re.id =? OR re.id IS NULL) AND r.facility =?" + 
					" ORDER BY u.fullname ASC";
		return jdbcTemplate.query(sql, new Object[] {event, facility}, new BeanPropertyRowMapper<EventRegistryStatus>(EventRegistryStatus.class));
	}


	public List<EventRegistryStatus> getEventRegistryStatusByEvent(Long event) {
		String sql ="SELECT u.fullname 'fullName',  " + 
				"		 r.facility , " + 
				"		 re.id 'eventId', " + 
				"		 re.reg_start 'eventRegStart', " + 
				"		 re.reg_end 'eventRegEnd', " + 
				"		 re.run_start 'eventRunStart', " + 
				"		 re.run_end 'eventRunEnd', " + 
				"		 u.email,  " + 
				"		 COALESCE(ec.category,'NOT REGISTERED') 'eventCategory',  " + 
				"		 COALESCE(reg.shirt_size,'N/A') 'shirtSize',reg.createdAt 'eventRegistered' " + 
				"  FROM users u " + 
				"  JOIN registrations r ON r.email = u.email  " + 
				"  LEFT JOIN run_event_reg reg ON reg.user_id = u.id " + 
				"  LEFT JOIN run_events re ON re.id = reg.event_id " + 
				"  LEFT JOIN event_categories ec ON reg.categ_id = ec.id " + 
				" WHERE re.id =? OR re.id IS NULL" + 
				" ORDER BY u.fullname ASC";
	return jdbcTemplate.query(sql, new Object[] {event}, new BeanPropertyRowMapper<EventRegistryStatus>(EventRegistryStatus.class));
	}


	public List<RunEvent> getRunEvents() {
		String sql ="SELECT re.id, re.reg_start regStart, re.reg_end regEnd, re.run_start runStart, re.run_end runEnd, re.createdAt, re.updatedAt "+
			    "  FROM run_events re ";
		return jdbcTemplate.query(sql,new BeanPropertyRowMapper<RunEvent>(RunEvent.class));
	}


	public void changeRegistrationCategory(RunEventDetail runEventDetail) {
		String sql ="UPDATE run_event_reg SET categ_id = ?, shirt_size = ?  WHERE user_id = ? AND event_id = ?";
		String sql2 ="UPDATE run_progress SET categ_id = ? WHERE user_id = ? AND event_id = ?";
		
		jdbcTemplate.update(sql, new Object[] {
			runEventDetail.getEventCateg().getId(),
			runEventDetail.getShirtSize(),
			runEventDetail.getUser().getId(),
			runEventDetail.getRunEvent().getId()
		});
		
		jdbcTemplate.update(sql2, new Object[] {
				runEventDetail.getEventCateg().getId(),
				runEventDetail.getUser().getId(),
				runEventDetail.getRunEvent().getId()
			});
		
	}
	
	public List<FacilityRunPercent> getFacilityRunPercentByEvent(Long eventId){
		String sql ="SELECT r.facility,rr.event_id eventId, " + 
				"		( " + 
				"		  SELECT  ROUND(SUM(rp.run_distance),2) facility_total_run " + 
				"		  FROM run_progress rp " + 
				"		  JOIN users u1 on u1.id = rp.user_id " + 
				"		  JOIN registrations r1 ON u1.email = r1.email " + 
				"		 WHERE rp.verified = 'VERIFIED' AND r1.facility = r.facility " + 
				"		 ) as totalUserDistance, " + 
				"( " + 
				"		  SELECT count(categ_id) " + 
				"		  FROM run_event_reg ecs " +  
				"		 WHERE ecs.categ_id = '3'" + 
				"		 ) as duathoncount, " + 
				"		 SUM(ec.distance) totalCategoryDistance " + 
				"  FROM users u " + 
				"  JOIN registrations r ON u.email = r.email " + 
				"  JOIN run_event_reg rr ON rr.user_id = u.id " + 
				"  JOIN event_categories ec ON rr.categ_id = ec.id " + 
				"  WHERE rr.event_id = ? AND r.reg_status = 1 " + 
				"  GROUP BY r.facility";
		return jdbcTemplate.query(sql, new Object[] {eventId}, new BeanPropertyRowMapper<FacilityRunPercent>(FacilityRunPercent.class));
	}
	public User getFacilitybyID(Long email){
		String sql="SELECT facility from regitrations WHERE email=?";
		return jdbcTemplate.queryForObject(sql, new Object[] { email }, new BeanPropertyRowMapper<User>(User.class));
	}
	
	public FacilityRunPercent getFacilityRunPercentByEvent(Long eventId, String facility){
		String sql ="SELECT r.facility,rr.event_id eventId,rr.approved As approved, " + 
				"		( " + 
				"		  SELECT  ROUND(SUM(rp.run_distance),2) facility_total_run " + 
				"		  FROM run_progress rp " + 
				"		  JOIN users u1 on u1.id = rp.user_id " + 
				"		  JOIN registrations r1 ON u1.email = r1.email " + 
				"		 WHERE rp.verified = 'VERIFIED' AND r1.facility = r.facility" + 
				"		 ) as totalUserDistance, " + 
				"( " + 
				"		  SELECT count(categ_id) " + 
				"		  FROM run_event_reg ecs " + 
				"		  JOIN users u1 on u1.id = ecs.user_id " + 
				"		  JOIN registrations r1 ON u1.email = r1.email " + 
				"		 WHERE ecs.categ_id = '3' AND r1.facility = r.facility" + 
				"		 ) as dua, " + 
				"( " + 
				"		  SELECT count(categ_id) " + 
				"		  FROM run_event_reg ecs " + 
				"		  JOIN users u1 on u1.id = ecs.user_id " + 
				"		  JOIN registrations r1 ON u1.email = r1.email " + 
				"		 WHERE ecs.categ_id = '2' AND r1.facility = r.facility" + 
				"		 ) as bikes, " + 
				"( " + 
				"		  SELECT count(categ_id) " + 
				"		  FROM run_event_reg ecsss " + 
				"		  JOIN users u1 on u1.id = ecsss.user_id " + 
				"		  JOIN registrations r1 ON u1.email = r1.email " + 
				"		 WHERE ecsss.categ_id = '1' AND r1.facility = r.facility" + 
				"		 ) as frun, " + 
		
				"( " +
				"		  SELECT ROUND(SUM(run_distance),0) " + 
				"		  FROM run_progress ecss " +  
				"		  JOIN users u1 on u1.id = ecss.user_id " + 
				"		  JOIN registrations r1 ON u1.email = r1.email " + 
				"		  JOIN run_event_reg u1s on u1s.user_id = ecss.user_id " +
				"		 WHERE ecss.categ_id='2' AND ecss.categ='RIDE' AND r1.facility = r.facility " + 
				"		 ) as bike, " + 
				"( " +
				"		  SELECT ROUND(SUM(run_distance),0) " + 
				"		  FROM run_progress ecss " +  
				"		  JOIN users u1 on u1.id = ecss.user_id " + 
				"		  JOIN registrations r1 ON u1.email = r1.email " + 
				"		  JOIN run_event_reg u1s on u1s.user_id = ecss.user_id " +
				"		 WHERE ecss.categ_id='3' AND ecss.categ='RIDE' AND r1.facility = r.facility " + 
				"		 ) as duacountbike, " + 
				"( " + 
				"		  SELECT ROUND(SUM(run_distance),0) " + 
				"		  FROM run_progress ecss " +  
				"		  JOIN users u1 on u1.id = ecss.user_id " + 
				"		  JOIN registrations r1 ON u1.email = r1.email " + 
				"		  JOIN run_event_reg u1s on u1s.user_id = ecss.user_id " +
				"		 WHERE ecss.categ_id = '3' AND ecss.categ='RUN' AND r1.facility = r.facility" + 
				"		 ) as run, " + 
				"		( " + 
				"		  SELECT  ROUND(SUM(rp.run_distance),2) facility_total_run " + 
				"		  FROM run_progress rp " + 
				"		  JOIN users u1 on u1.id = rp.user_id " + 
				"		  JOIN registrations r1 ON u1.email = r1.email " + 
				"		 WHERE rp.verified = 'VERIFIED' AND r1.facility = r.facility AND rp.categ='RUN'" + 
				"		 ) as runapproved, " + 
				"		( " + 
				"		  SELECT  ROUND(SUM(rp.run_distance),2) facility_total_run " + 
				"		  FROM run_progress rp " + 
				"		  JOIN users u1 on u1.id = rp.user_id " + 
				"		  JOIN registrations r1 ON u1.email = r1.email " + 
				"		 WHERE rp.verified = 'VERIFIED' AND r1.facility = r.facility AND rp.categ='RIDE'" + 
				"		 ) as bikeapproved, " + 
				"( " + 
				"		  SELECT ROUND(SUM(run_distance),0) " + 
				"		  FROM run_progress ecss " +  
				"		  JOIN users u1 on u1.id = ecss.user_id " + 
				"		  JOIN registrations r1 ON u1.email = r1.email " + 
				"		  JOIN run_event_reg u1s on u1s.user_id = ecss.user_id " +
				"		 WHERE ecss.categ_id = '1' AND ecss.categ='RUN' AND r1.facility = r.facility" + 
				"		 ) as runsumcount, " + 
				"		 SUM(ec.distance) totalCategoryDistance " + 
				"  FROM users u " + 
				"  JOIN registrations r ON u.email = r.email " + 
				"  JOIN run_event_reg rr ON rr.user_id = u.id " + 
				"  JOIN event_categories ec ON rr.categ_id = ec.id " + 
				"  WHERE rr.event_id = ? AND r.reg_status = 1 AND r.facility =?";
		return jdbcTemplate.queryForObject(sql, new Object[] { eventId, facility }, new BeanPropertyRowMapper<FacilityRunPercent>(FacilityRunPercent.class));
	}
	
}
