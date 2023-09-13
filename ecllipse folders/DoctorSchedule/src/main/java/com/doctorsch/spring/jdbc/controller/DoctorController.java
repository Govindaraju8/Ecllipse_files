package com.doctorsch.spring.jdbc.controller;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.doctorsch.spring.jdbc.dao.DoctorDAO;
import com.doctorsch.spring.jdbc.model.Doctor;

@Controller
public class DoctorController {
	DoctorDAO doctordao;

	@Autowired
	public DoctorController(DoctorDAO doctordao) {
		this.doctordao = doctordao;
	}

	@RequestMapping(value = "/user", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		System.out.println("Home Page Requested, locale = " + locale);

		return "home";
	}

	@RequestMapping(value = "/raju", method = RequestMethod.POST)
	public String user(@ModelAttribute("doc") Doctor doc, Model model) {
		doctordao.createDoctorSchedule(doc);
		System.out.println("User Page Requested");
		model.addAttribute("empName", doc.getDoct_id());
		return "raju";
	}

}
