package com.example;

// model class
public class Accountants {
	private int Empno;
	private String ename;
	private String job;
	private double sal;

	public Accountants(int empno, String ename, String job, double sal) {
		super();
		Empno = empno;
		this.ename = ename;
		this.job = job;
		this.sal = sal;

	}

	public int getEmpno() {
		return Empno;
	}

	public void setEmpno(int empno) {
		Empno = empno;
	}

	public String getEname() {
		return ename;
	}

	public void setEname(String ename) {
		this.ename = ename;
	}

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public double getSal() {
		return sal;
	}

	public void setSal(double sal) {
		this.sal = sal;
	}

}
