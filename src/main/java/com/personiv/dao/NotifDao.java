package com.personiv.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.personiv.controller.NotificationController;
import com.personiv.model.Notification;

@Repository
@Transactional(readOnly = false)
public class NotifDao extends JdbcDaoSupport{
	
	private JdbcTemplate jdbcTemplate;

    @Autowired
    private DataSource dataSource;
    
    @PostConstruct
    private void initialize() {
        setDataSource(dataSource);
        jdbcTemplate = getJdbcTemplate();
    }
    
    public void sendAdminNotification(Notification notif) {
    	
    	String sql ="INSERT INTO notifications(recipient,title,body,click_action,createdAt)" + 
    			"SELECT u.id,?,?,?,CURRENT_TIMESTAMP " + 
    			"  FROM users u" + 
    			" WHERE u.role ='ADMIN'";
    	jdbcTemplate.update(sql, new Object[] {
    			notif.getRecipient(),
    			notif.getTitle(),
    			notif.getBody()
    	});
    }
    
    public List<Notification> getNotification(Long id){
    	String sql ="SELECT * FROM notifications where recipient = ? AND viewed = 0";
    	return jdbcTemplate.query(sql,new Object[] {id}, new BeanPropertyRowMapper<Notification>(Notification.class));
    }

	public void viewNotif(Notification notif) {
		
		
		String sql ="UPDATE notifications SET viewed = 1, updatedAt = CURRENT_TIMESTAMP WHERE id =?";
		
		jdbcTemplate.update(sql, new Object[] {notif.getId()});
		
	}
	
	public void viewAllNotif(List<Notification> notif) {
		String sql ="UPDATE notifications SET viewed = 1, updatedAt = CURRENT_TIMESTAMP WHERE id =?";
		
		jdbcTemplate.batchUpdate(sql, new NotifBatchUpdate(notif));
		
	}
	
	
	/*Helper class for batch update*/
	private class NotifBatchUpdate implements BatchPreparedStatementSetter{

		private List<Notification> notifs;
		
		public NotifBatchUpdate(List<Notification> notifs) {
			this.notifs = notifs;
		}
		
		@Override
		public int getBatchSize() {
			return notifs.size();
		}

		@Override
		public void setValues(PreparedStatement ps, int index) throws SQLException {
			Notification notif = notifs.get(index);
			ps.setLong(1, notif.getId());
			
		}
		
	}
}
