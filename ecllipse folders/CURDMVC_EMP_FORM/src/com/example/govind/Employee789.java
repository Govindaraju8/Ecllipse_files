package com.example.govind;

public class Employee789 {
	private int empno;
	private String empname;
	private String job;
	private double salary;
	private String department;

	// Constructors
	public Employee789(int empno, String empname, String job, double salary, String department) {
		this.empno = empno;
		this.empname = empname;
		this.job = job;
		this.salary = salary;
		this.department = department;
	}

	public Employee789() {
		// Default constructor
	}

	// Getter and Setter methods
	public int getEmpno() {
		return empno;
	}

	public void setEmpno(int empno) {
		this.empno = empno;
	}

	public String getEmpname() {
		return empname;
	}

	public void setEmpname(String empname) {
		this.empname = empname;
	}

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

}
