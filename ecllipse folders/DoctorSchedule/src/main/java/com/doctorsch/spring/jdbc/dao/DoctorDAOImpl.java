package com.doctorsch.spring.jdbc.dao;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import com.doctorsch.spring.jdbc.model.Doctor;

public class DoctorDAOImpl implements DoctorDAO {
	JdbcTemplate jdbcTemplate;
	private final String SQL_INSERT_DOCTORSCHEDULE = "INSERT INTO DoctorSchedule21 (doct_id, doct_sindex, dcsc_schedule, dcsc_avl_from, dcsc_avl_to, dcsc_app_slot) VALUES (?, ?, ?, ?, ?, ?)";

	// private final String SQL_GET_ALL = "SELECT * FROM DoctorSchedule21";

	@Autowired
	public DoctorDAOImpl(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public boolean createDoctorSchedule(Doctor d) {
		// TODO Auto-generated method stub
		return jdbcTemplate.update(SQL_INSERT_DOCTORSCHEDULE, d.getDoct_id(), d.getDoct_sindex(), d.getDcsc_schedule(),
				d.getDcsc_avl_from(), d.getDcsc_avl_to(), d.getDcsc_app_slot()) > 0;

	}

}
