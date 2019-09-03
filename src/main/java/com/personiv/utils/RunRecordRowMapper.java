package com.personiv.utils;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.personiv.model.EventCategory;
import com.personiv.model.RunEvent;
import com.personiv.model.RunRecord;
import com.personiv.model.User;

public class RunRecordRowMapper implements RowMapper<RunRecord>{

	@Override
	public RunRecord mapRow(ResultSet rs, int arg1) throws SQLException {

		RunRecord rp = new RunRecord();
		
		User user = new User();
		user.setId(rs.getLong("userId"));
		user.setEmail(rs.getString("email"));
		user.setFullname(rs.getString("userFullname"));
		
		RunEvent event = new RunEvent();
		event.setId(rs.getLong("eventId"));
		event.setRegStart(rs.getTimestamp("eventRegStart"));
		event.setRegEnd(rs.getTimestamp("eventRegEnd"));
		event.setRunStart(rs.getTimestamp("eventRunStart"));
		event.setRunEnd(rs.getTimestamp("eventRunEnd"));
		
		EventCategory category = new EventCategory();
		category.setId(rs.getLong("categId"));
		category.setCategory(rs.getString("categName"));
		category.setDescription(rs.getString("categDesc"));
		category.setDistance(rs.getFloat("categDistance"));
		
		rp.setUser(user);
		rp.setEvent(event);
		rp.setCategory(category);
		rp.setDistance(rs.getFloat("distanceTraveled"));
		rp.setTime(rs.getTime("totalElapseTime"));
		
			
		// verified = rs.getString("rpVerified"); //hack to convert boolean to object
		
		//should have use enum instead of boolean
		//Boolean ver = verified == null ? null : verified.equals("0") ? false : true ;

		
		rp.setVerified(rs.getString("rpVerified"));
		rp.setCateg(rs.getString("categ"));
		rp.setGallery(rs.getString("rpGallery"));
	
		rp.setFileName(rs.getString("file_name"));
		rp.setFilePath(rs.getString("file_path"));
		rp.setId(rs.getLong("id"));
		rp.setCreatedAt(rs.getTimestamp("rpCreatedAt"));
		
		return rp;
	}

}
