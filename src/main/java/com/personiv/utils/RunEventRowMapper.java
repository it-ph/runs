package com.personiv.utils;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.personiv.model.RunEvent;

public class RunEventRowMapper implements RowMapper<RunEvent>{

	@Override
	public RunEvent mapRow(ResultSet rs, int arg1) throws SQLException {
		RunEvent run = new RunEvent();
		
		return run;
	}

}
