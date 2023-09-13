package com.example.govind;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DALclass {

	public static boolean addEmployee(Employee789 employee) {
		boolean success = false;

		try {
			String url = "jdbc:postgresql://192.168.110.48:5432/plf_training";
			String username = "plf_training_admin";
			String password = "pff123";
			Class.forName("org.postgresql.Driver");
			Connection cn = DriverManager.getConnection(url, username, password);
			String insertSql = "INSERT INTO employee789 (empno,empname, job, salary,department) VALUES (?, ?, ?, ?, ?)";
			PreparedStatement preparedStatement = cn.prepareStatement(insertSql);
			preparedStatement.setInt(1, employee.getEmpno());
			preparedStatement.setString(2, employee.getEmpname());
			preparedStatement.setString(3, employee.getJob());
			preparedStatement.setDouble(4, employee.getSalary());
			preparedStatement.setString(5, employee.getDepartment());

			int rowsAffected = preparedStatement.executeUpdate();

			if (rowsAffected > 0) {
				success = true;
			}

		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}

		return success;
	}
}
