package g;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/fetch")
public class FetchServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	final String url = "jdbc:postgresql://192.168.110.48:5432/plf_training";
	final String username = "plf_training_admin";
	final String password = "pff123";
	final String DRIVER = "org.postgresql.Driver";
	Connection conn = null;

	public void init() throws ServletException {

		// Database connection through Driver Manager
		try {
			Class.forName(DRIVER);
			conn = DriverManager.getConnection(url, username, password);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try {

			// Set the response content type and
			// get the PrintWriter object.
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();

			// Set up HTML table formatting for the output
			out.println("<html><body>");
			out.println("<h3>Mobile Phone Details</h3>");
			out.println("<table border=1><tr>" + "<td><b>S.No</b></td>" + "<td><b>Brand</b></td>"
					+ "<td><b>Processor</b></td>" + "<td><b>Operating System</b></td>"
					+ "<td><b>Screen Size(inches)</b></td>" + "<td><b>Battery Life(mAh)</b></td></tr>");

			// Create JDBC statement object, construct
			// the SQL query and execute the query.
			Statement stmt = conn.createStatement();
			String sql = "select * from mobilePhones;";
			ResultSet rs = stmt.executeQuery(sql);

			// Loop through the result set to
			// retrieve the individual data items.
			while (rs.next()) {
				int sno = rs.getInt("sno");
				String brand = rs.getString("brand");
				String processor = rs.getString("processor");
				float screenSize = rs.getFloat("screensize");
				String osystem = rs.getString("operatingsystem");
				int batteryLife = rs.getInt("batterylife");

				out.println("<tr>" + "<td>" + sno + "</td>" + "<td>" + brand + "</td>" + "<td>" + processor + "</td>"
						+ "<td>" + osystem + "</td>" + "<td>" + screenSize + "</td>" + "<td>" + batteryLife
						+ "</td></tr>");

			}
			out.println("</table></body></html>");

			// Close Result set, Statement
			// and PrintWriter objects.
			rs.close();
			stmt.close();
			out.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void destroy() {

		// Close connection object.
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
