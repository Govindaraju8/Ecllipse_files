package com.example;

class Employee {
	private String name;
	private String number;
	private String job;
	private String salary;

	public Employee() {
		// Default constructor
	}

	public Employee(String name, String number, String job, String salary) {
		this.name = name;
		this.number = number;
		this.job = job;
		this.salary = salary;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public String getSalary() {
		return salary;
	}

	public void setSalary(String salary) {
		this.salary = salary;
	}
}
