package com.doctorsch.spring.jdbc.dao;

import com.doctorsch.spring.jdbc.model.Doctor;

public interface DoctorDAO {

	boolean createDoctorSchedule(Doctor d);

	// List<Doctor> getAllDoctorSchedules();

}
