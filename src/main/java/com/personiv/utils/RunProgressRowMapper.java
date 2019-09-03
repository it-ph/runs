package com.personiv.utils;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.personiv.model.EventCategory;
import com.personiv.model.RunEvent;
import com.personiv.model.RunProgress;
import com.personiv.model.User;

public class RunProgressRowMapper implements RowMapper<RunProgress>{

	@Override
	public RunProgress mapRow(ResultSet rs, int arg1) throws SQLException {
		
		RunProgress rp = new RunProgress();
		
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
		rp.setEventCategory(category);
		rp.setDistanceTraveled(rs.getFloat("distanceTraveled"));
		rp.setTotalElapseTime(rs.getString("totalElapseTime"));
		rp.setCreatedAt(rs.getTimestamp("rpCreated"));
		
		rp.setPace(rs.getDouble("pace"));
		return rp;
	}

}
