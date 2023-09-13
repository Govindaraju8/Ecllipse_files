package com.doctorsch.spring.jdbc.model;

import java.time.LocalTime;

public class Doctor {
	private int doct_id;
	private int doct_sindex;
	private String dcsc_schedule;
	private LocalTime dcsc_avl_from;
	private LocalTime dcsc_avl_to;
	private LocalTime dcsc_app_slot;

	public Doctor() {

	}

	public int getDoct_id() {
		return doct_id;
	}

	public void setDoct_id(int doct_id) {
		this.doct_id = doct_id;
	}

	public int getDoct_sindex() {
		return doct_sindex;
	}

	public void setDoct_sindex(int doct_sindex) {
		this.doct_sindex = doct_sindex;
	}

	public String getDcsc_schedule() {
		return dcsc_schedule;
	}

	public void setDcsc_schedule(String dcsc_schedule) {
		this.dcsc_schedule = dcsc_schedule;
	}

	public LocalTime getDcsc_avl_from() {
		return dcsc_avl_from;
	}

	public void setDcsc_avl_from(LocalTime dcsc_avl_from) {
		this.dcsc_avl_from = dcsc_avl_from;
	}

	public LocalTime getDcsc_avl_to() {
		return dcsc_avl_to;
	}

	public void setDcsc_avl_to(LocalTime dcsc_avl_to) {
		this.dcsc_avl_to = dcsc_avl_to;
	}

	public LocalTime getDcsc_app_slot() {
		return dcsc_app_slot;
	}

	public void setDcsc_app_slot(LocalTime dcsc_app_slot) {
		this.dcsc_app_slot = dcsc_app_slot;
	}

}
