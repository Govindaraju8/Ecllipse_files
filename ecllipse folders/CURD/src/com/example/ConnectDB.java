package com.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ConnectDB {
	public ArrayList<Accountants> connection() throws SQLException, ClassNotFoundException {
		// postgres database connection
		Class.forName("org.postgresql.Driver");

		String url = "jdbc:postgresql://192.168.110.48:5432/plf_training?user=plf_training_admin&password=pff123";
		Connection conn = DriverManager.getConnection(url);
		Statement st = conn.createStatement();
		ArrayList<Accountants> em = new ArrayList<Accountants>();
		ResultSet rs = st.executeQuery("select * from Accountants");
		while (rs.next()) {
			em.add(new Accountants(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDouble(4)));
		}

		return em;
	}

	public void delete(int s) throws Exception {
		Class.forName("org.postgresql.Driver");
		String url = "jdbc:postgresql://192.168.110.48:5432/plf_training?user=plf_training_admin&password=pff123";
		Connection conn = DriverManager.getConnection(url);
		PreparedStatement st = conn.prepareStatement("delete from Accountants where empno=?");
		st.setInt(1, s);
		st.executeUpdate();
		connection();
	}

	public void Add(Accountants e) {
		Connection conn = null;
		PreparedStatement st = null;
		try {
			Class.forName("org.postgresql.Driver");
			conn = DriverManager.getConnection(
					"jdbc:postgresql://192.168.110.48:5432/plf_training?user=plf_training_admin&password=pff123");
			st = conn.prepareStatement("insert into Accountants values(?,?,?,?)");
			st.setInt(1, e.getEmpno());
			st.setString(2, e.getEname());
			st.setString(3, e.getJob());
			st.setDouble(4, e.getSal());

			st.executeUpdate();

		} catch (Exception ee) {
			ee.printStackTrace();
		}

	}

	public void update(Accountants e) throws SQLException, ClassNotFoundException {
		Class.forName("org.postgresql.Driver");
		String url = "jdbc:postgresql://192.168.110.48:5432/plf_training?user=plf_training_admin&password=pff123";
		Connection conn = DriverManager.getConnection(url);
		PreparedStatement st = conn
				.prepareStatement("UPDATE Accountants SET empno=?, ename=?, job=?, sal=? WHERE empno=?");
		st.setInt(1, e.getEmpno());
		st.setString(2, e.getEname());
		st.setString(3, e.getJob());
		st.setDouble(4, e.getSal());
		st.setInt(5, e.getEmpno());
		st.executeUpdate();
	}

}
