package com.example;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

@WebServlet("/Servlet")
public class Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Servlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		ConnectDB data = new ConnectDB();
		int c = 1;
		try {
			JSONObject obj = new JSONObject();

			ArrayList<Accountants> emp = data.connection();
			Accountants e = null;
			String btn = request.getParameter("id");
			if (btn.equals("first")) {
				e = emp.get(0);
				obj.put("id", e.getEmpno());
				obj.put("name", e.getEname());
				obj.put("job", e.getJob());
				obj.put("sal", e.getSal());

				response.getWriter().write(obj.toString());
			} else if (btn.equals("last")) {
				e = emp.get(emp.size() - 1);
				obj.put("id", e.getEmpno());
				obj.put("name", e.getEname());
				obj.put("job", e.getJob());
				obj.put("sal", e.getSal());

				response.getWriter().write(obj.toString());
			}

			else if (btn.equals("Next")) {
				int p = Integer.parseInt(request.getParameter("p"));

				e = emp.get(p);
				obj.put("id", e.getEmpno());
				obj.put("name", e.getEname());
				obj.put("job", e.getJob());
				obj.put("sal", e.getSal());

				response.getWriter().write(obj.toString());

			} else if (btn.equals("prev")) {

				int p = Integer.parseInt(request.getParameter("p"));

				e = emp.get(p);
				obj.put("id", e.getEmpno());
				obj.put("name", e.getEname());
				obj.put("job", e.getJob());
				obj.put("sal", e.getSal());

				response.getWriter().write(obj.toString());

			} else if (btn.equals("del")) {
				int empno = Integer.parseInt(request.getParameter("empno"));
				int g = 0;
				for (int i = 0; i < emp.size(); i++) {
					if (emp.get(i).getEmpno() == (empno)) {
						g = i;
					}
				}
				emp.remove(g);
				data.delete(empno);
			} else if (btn.equals("Add")) {
				System.out.println(request.getParameter("a"));
				int s1 = Integer.parseInt(request.getParameter("a"));
				String s2 = request.getParameter("b");
				String s3 = request.getParameter("c");
				double s4 = Double.parseDouble(request.getParameter("d"));
				Accountants empo = new Accountants(s1, s2, s3, s4);
				data.Add(empo);

			} else if (btn.equals("edit")) {
				int empno = Integer.parseInt(request.getParameter("a"));
				String name = request.getParameter("b");
				String job = request.getParameter("c");
				double sal = Double.parseDouble(request.getParameter("d"));

				// Update Accountants data in your ArrayList or database
				Accountants updatedAccountants = new Accountants(empno, name, job, sal);
				// You need to implement your update logic here

				// Prepare JSON response with updated data
				// JSONObject obj = new JSONObject();
				data.update(updatedAccountants);
				/*
				 * obj.put("id", updatedAccountants.getEmpno()); obj.put("name", updatedAccountants.getEname());
				 * obj.put("job", updatedAccountants.getJob()); obj.put("sal", updatedAccountants.getSal());
				 */
				// response.getWriter().write(obj.toString());
			}
		} catch (

		Exception e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
