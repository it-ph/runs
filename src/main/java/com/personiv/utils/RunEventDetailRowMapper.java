package com.personiv.utils;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.personiv.model.Entitlement;
import com.personiv.model.EventCategory;
import com.personiv.model.RunEvent;
import com.personiv.model.RunEventDetail;
import com.personiv.model.User;

public class RunEventDetailRowMapper implements RowMapper<RunEventDetail>{
	
	@Override
	public RunEventDetail mapRow(ResultSet rs, int i) throws SQLException {
		
		User u = new User();
		
		
		u.setId(rs.getLong("userId"));
		u.setEmail(rs.getString("email"));
		u.setFullname(rs.getString("fullname"));
		u.setCreatedAt(rs.getTimestamp("userCreated"));
		u.setUpdatedAt(rs.getTimestamp("userUpdated"));
		
		RunEvent run = new RunEvent();
		run.setId(rs.getLong("eventId"));
		run.setRegStart(rs.getTimestamp("regStart"));
		run.setRegEnd(rs.getTimestamp("regEnd"));
		run.setRunStart(rs.getTimestamp("runStart"));
		run.setRunEnd(rs.getTimestamp("runEnd"));
		run.setCreatedAt(rs.getTimestamp("eventCreated"));
		run.setUpdatedAt(rs.getDate("eventUpdated"));
		
		EventCategory categ = new EventCategory();
		categ.setId(rs.getLong("categId"));
		categ.setCategory(rs.getString("category"));
		categ.setDistance(rs.getFloat("distance"));
		categ.setCreatedAt(rs.getDate("categCreated"));
		categ.setUpdatedAt(rs.getDate("categUpdated"));
		
		
//		Entitlement ent = new Entitlement();
//		ent.setId(rs.getLong("entId"));
//		ent.setName(rs.getString("entName"));
//		ent.setCost(rs.getFloat("entCost"));
//		ent.setCreatedAt(rs.getTimestamp("entCreated"));
//		ent.setUpdatedAt(rs.getTimestamp("entUpdated"));
		
		RunEventDetail rep = new RunEventDetail();
		
		

		
		rep.setUser(u);
		rep.setRunEvent(run);
		rep.setEventCateg(categ);
//		rep.setEntitlement(ent);
		rep.setApproved(rs.getBoolean("approved"));
		rep.setShirtSize(rs.getString("shirtSize"));
		rep.setCreatedAt(rs.getTimestamp("reCreated"));
		rep.setUpdatedAt(rs.getTimestamp("reUpdated"));
		rep.setForhim(rs.getString("Hima"));
		
		return rep;
	}

}
