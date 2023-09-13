package com.example;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONException;
import org.json.JSONObject;

@WebServlet("/EmployeeServlet")
public class EmployeeServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Connection connection = null;
	PreparedStatement preparedStatement = null;
	ResultSet resultSet = null;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String buttonClicked = request.getParameter("button");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		try {
			Class.forName("org.postgresql.Driver");
			Connection connection = JDBCHelper.getConnection();

			String sql = "SELECT * FROM satish_k";
			Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			ResultSet resultSet = statement.executeQuery(sql);
			ArrayList<Employee> employeeList = new ArrayList<Employee>();
			while (resultSet.next()) {
				String name = resultSet.getString("name");
				String number = resultSet.getString("number");
				String job = resultSet.getString("job");
				String salary = resultSet.getString("salary");

				Employee employee = new Employee();
				employee.setName(name);
				employee.setNumber(number);
				employee.setJob(job);
				employee.setSalary(salary);

				employeeList.add(employee);
			}

			HttpSession session = request.getSession();

			Integer rowindex = (Integer) session.getAttribute("rowindex");
			String but = (String) session.getAttribute("but");

			if (rowindex == null) {
				rowindex = 0;
			}
			if (but == null) {
				but = "";
			}
			if (buttonClicked.equals("First")) {
				if (!employeeList.isEmpty()) {
					Employee firstEmployee = employeeList.get(0);
					JSONObject jsonResponse = new JSONObject();
					jsonResponse.put("name", firstEmployee.getName());
					jsonResponse.put("number", firstEmployee.getNumber());
					jsonResponse.put("job", firstEmployee.getJob());
					jsonResponse.put("salary", firstEmployee.getSalary());

					response.setContentType("application/json");
					response.setCharacterEncoding("UTF-8");
					response.getWriter().write(jsonResponse.toString());
					session.setAttribute("rowindex", 0);
				}
			} else if (buttonClicked.equals("Last")) {
				if (!employeeList.isEmpty()) {
					Employee lastEmployee = employeeList.get(employeeList.size() - 1);
					JSONObject jsonResponse = new JSONObject();
					jsonResponse.put("name", lastEmployee.getName());
					jsonResponse.put("number", lastEmployee.getNumber());
					jsonResponse.put("job", lastEmployee.getJob());
					jsonResponse.put("salary", lastEmployee.getSalary());

					response.setContentType("application/json");
					response.setCharacterEncoding("UTF-8");
					response.getWriter().write(jsonResponse.toString());
					rowindex = employeeList.size() - 1;
					session.setAttribute("rowindex", rowindex);
				}
			} else if (buttonClicked.equals("Prev")) {
				if (!employeeList.isEmpty() && rowindex > 0) {
					rowindex--;
					Employee previousEmployee = employeeList.get(rowindex);
					JSONObject jsonResponse = new JSONObject();
					jsonResponse.put("name", previousEmployee.getName());
					jsonResponse.put("number", previousEmployee.getNumber());
					jsonResponse.put("job", previousEmployee.getJob());
					jsonResponse.put("salary", previousEmployee.getSalary());

					response.setContentType("application/json");
					response.setCharacterEncoding("UTF-8");
					response.getWriter().write(jsonResponse.toString());
					session.setAttribute("rowindex", rowindex);
				}
			} else if (buttonClicked.equals("Next")) {
				if (!employeeList.isEmpty() && rowindex < employeeList.size() - 1) {
					rowindex++;
					Employee nextEmployee = employeeList.get(rowindex);
					JSONObject jsonResponse = new JSONObject();
					jsonResponse.put("name", nextEmployee.getName());
					jsonResponse.put("number", nextEmployee.getNumber());
					jsonResponse.put("job", nextEmployee.getJob());
					jsonResponse.put("salary", nextEmployee.getSalary());

					response.setContentType("application/json");
					response.setCharacterEncoding("UTF-8");
					response.getWriter().write(jsonResponse.toString());
					session.setAttribute("rowindex", rowindex);
				}
			} else if (buttonClicked.equals("Add")) {
				session.setAttribute("but", "Add");
				JSONObject jsonResponse = new JSONObject();
				jsonResponse.put("message", "Click save for succesfully adding");

				response.setContentType("application/json");
				response.setCharacterEncoding("UTF-8");
				response.getWriter().write(jsonResponse.toString());
			} else if (buttonClicked.equals("Edit")) {
				session.setAttribute("but", "Edit");
				JSONObject jsonResponse = new JSONObject();
				jsonResponse.put("message", "Click save for succesfully editing");

				response.setContentType("application/json");
				response.setCharacterEncoding("UTF-8");
				response.getWriter().write(jsonResponse.toString());
			} else if (buttonClicked.equals("Delete")) {
				session.setAttribute("but", "Delete");
				JSONObject jsonResponse = new JSONObject();
				jsonResponse.put("message", "Click save for succesfully Deleting");

				response.setContentType("application/json");
				response.setCharacterEncoding("UTF-8");
				response.getWriter().write(jsonResponse.toString());
			} else if (buttonClicked.equals("Search")) {
				String name = request.getParameter("name");

				// Query the database based on the search name
				String searchSql = "SELECT * FROM satish_k WHERE name=?";
				PreparedStatement searchStatement = connection.prepareStatement(searchSql);
				searchStatement.setString(1, name);

				ResultSet searchResultSet = searchStatement.executeQuery();

				if (searchResultSet.next()) {
					// Found a matching record
					Employee searchEmployee = new Employee(searchResultSet.getString("name"),
							searchResultSet.getString("number"), searchResultSet.getString("job"),
							searchResultSet.getString("salary"));

					JSONObject jsonResponse = new JSONObject();
					jsonResponse.put("name", searchEmployee.getName());
					jsonResponse.put("number", searchEmployee.getNumber());
					jsonResponse.put("job", searchEmployee.getJob());
					jsonResponse.put("salary", searchEmployee.getSalary());

					response.setContentType("application/json");
					response.setCharacterEncoding("UTF-8");
					response.getWriter().write(jsonResponse.toString());
				} else {
					// No matching record found
					JSONObject jsonResponse = new JSONObject();
					jsonResponse.put("message", "No matching record found");

					response.setContentType("application/json");
					response.setCharacterEncoding("UTF-8");
					response.getWriter().write(jsonResponse.toString());
				}

				// Close resources
				JDBCHelper.closeResultSet(searchResultSet);
				JDBCHelper.closePreparedStatement(searchStatement);
			}

			else if (buttonClicked.equals("Save")) {
				if (but.equals("Add")) {
					String name = request.getParameter("name");
					String number = request.getParameter("number");
					String job = request.getParameter("job");
					String salary = request.getParameter("salary");

					Employee employee = new Employee();
					employee.setName(name);
					employee.setNumber(number);
					employee.setJob(job);
					employee.setSalary(salary);

					employeeList.add(employee);

					// Insert the data into the database
					String sql1 = "INSERT INTO satish_k (name, number, job, salary) VALUES (?, ?, ?, ?)";
					PreparedStatement preparedStatement1 = connection.prepareStatement(sql1);
					preparedStatement1.setString(1, name);
					preparedStatement1.setString(2, number);
					preparedStatement1.setString(3, job);
					preparedStatement1.setString(4, salary);

					int rowsAffected = preparedStatement1.executeUpdate();

					if (rowsAffected > 0) {
						// Data inserted successfully
						JSONObject jsonResponse = new JSONObject();
						jsonResponse.put("message", "Data inserted successfully");

						response.setContentType("application/json");
						response.setCharacterEncoding("UTF-8");
						response.getWriter().write(jsonResponse.toString());
					} else {
						// Error occurred during insertion
						JSONObject jsonResponse = new JSONObject();
						jsonResponse.put("message", "Error occurred during insertion");

						response.setContentType("application/json");
						response.setCharacterEncoding("UTF-8");
						response.getWriter().write(jsonResponse.toString());
					}
					session.setAttribute("rowindex", employeeList.size() - 1);
					preparedStatement1.close();
				}

				else if (but.equals("Edit")) {
					System.out.println(rowindex);
					String name = request.getParameter("name");
					String number = request.getParameter("number");
					String job = request.getParameter("job");
					String salary = request.getParameter("salary");

					Employee employee = new Employee();
					employee.setName(name);
					employee.setNumber(number);
					employee.setJob(job);
					employee.setSalary(salary);

					employeeList.set(rowindex, employee);
					String updateSql = "UPDATE satish_k SET number=?, job=?, salary=? where name=?";
					PreparedStatement updateStatement = connection.prepareStatement(updateSql);
					updateStatement.setString(1, number);
					updateStatement.setString(2, job);
					updateStatement.setString(3, salary);
					updateStatement.setString(4, name);

					int rowsAffected = updateStatement.executeUpdate();

					if (rowsAffected > 0) {
						// Data inserted successfully
						JSONObject jsonResponse = new JSONObject();
						jsonResponse.put("message", "Data edited successfully");

						response.setContentType("application/json");
						response.setCharacterEncoding("UTF-8");
						response.getWriter().write(jsonResponse.toString());
					} else {
						// Error occurred during insertion
						JSONObject jsonResponse = new JSONObject();
						jsonResponse.put("message", "Error occurred during insertion");

						response.setContentType("application/json");
						response.setCharacterEncoding("UTF-8");
						response.getWriter().write(jsonResponse.toString());
					}

					updateStatement.close();
				} else if (but.equals("Delete")) {
					System.out.println(rowindex);
					String name = request.getParameter("name");
					String number = request.getParameter("number");
					String job = request.getParameter("job");
					String salary = request.getParameter("salary");
					employeeList.remove(rowindex);
					String updateSql = "Delete from satish_k where name=?";
					PreparedStatement updateStatement = connection.prepareStatement(updateSql);

					updateStatement.setString(1, name);

					int rowsAffected = updateStatement.executeUpdate();

					if (rowsAffected > 0) {
						// Data Deleted successfully
						JSONObject jsonResponse = new JSONObject();
						jsonResponse.put("message", "Data Deleted successfully");

						response.setContentType("application/json");
						response.setCharacterEncoding("UTF-8");
						response.getWriter().write(jsonResponse.toString());
					} else {
						// Error occurred during insertion
						JSONObject jsonResponse = new JSONObject();
						jsonResponse.put("message", "Error occurred during insertion");

						response.setContentType("application/json");
						response.setCharacterEncoding("UTF-8");
						response.getWriter().write(jsonResponse.toString());
					}

					updateStatement.close();
				}

			}
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
			out.println(e);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCHelper.closeResultSet(resultSet);
			JDBCHelper.closePreparedStatement(preparedStatement);
			JDBCHelper.closeConnection(connection);
			out.close();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
