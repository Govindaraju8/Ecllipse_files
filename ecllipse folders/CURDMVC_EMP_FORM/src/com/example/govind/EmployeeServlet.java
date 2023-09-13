package com.example.govind;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONException;
import org.json.JSONObject;

// @WebServlet("/EmployeeServlet")
public class EmployeeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public EmployeeServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String buttonClicked = request.getParameter("button");
		HttpSession session = request.getSession();

		Integer rowindex = (Integer) session.getAttribute("rowindex");
		String but = (String) session.getAttribute("but");

		if (rowindex == null) {
			rowindex = 0;
		}
		if (but == null) {
			but = "";
		}

		if (buttonClicked.equals("Add")) {
			session.setAttribute("but", "Add");
			int empno = Integer.parseInt(request.getParameter("number"));
			String empname = request.getParameter("name");
			String job = request.getParameter("job");
			double salary = Double.parseDouble(request.getParameter("salary"));
			String department = request.getParameter("department");

			Employee789 newEmployee = new Employee789(empno, empname, job, salary, department);

			boolean addSuccess = DALclass.addEmployee(newEmployee);

			JSONObject jsonResponse = new JSONObject();
			if (addSuccess) {
				try {
					jsonResponse.put("message", "Click save for succesfully adding");
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {
				try {
					jsonResponse.put("message", "Error occurred during addition");
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(jsonResponse.toString());
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
