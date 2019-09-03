package com.personiv.utils;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.personiv.model.EventCategory;
import com.personiv.model.RunEvent;
import com.personiv.model.RunEventCategory;

public class RunEventCategoryRowMapper implements RowMapper<RunEventCategory> {

	@Override
	public RunEventCategory mapRow(ResultSet rs, int arg1) throws SQLException {
	
		RunEventCategory rec = new RunEventCategory();
		
	
		
		RunEvent run = new RunEvent();
		run.setId(rs.getLong("eventId"));
		run.setRegStart(rs.getTimestamp("reg_start"));
		run.setRegEnd(rs.getTimestamp("reg_end"));
		run.setRunStart(rs.getTimestamp("run_start"));
		run.setRunEnd(rs.getTimestamp("run_end"));
		run.setCreatedAt(rs.getTimestamp("runCreated"));
		run.setUpdatedAt(rs.getTimestamp("runUpdated"));
		
		
		rec.setRunEvent(run);
		
		EventCategory ec = new EventCategory();
		
		ec.setId(rs.getLong("categId"));
		ec.setDistance(rs.getFloat("distance"));
		ec.setCategory(rs.getString("category"));
		ec.setCreatedAt(rs.getTimestamp("categCreated"));
		ec.setUpdatedAt(rs.getTimestamp("categUpdated"));
		
		rec.setEventCategory(ec);
		
		rec.setCreatedAt(rs.getTimestamp("reCreated"));
		rec.setUpdatedAt(rs.getTimestamp("reUpdated"));
		rec.setApproved(rs.getBoolean("approved"));
		return rec;
	}

}
