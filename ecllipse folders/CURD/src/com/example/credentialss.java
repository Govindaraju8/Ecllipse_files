package com.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;

public class credentialss {
	private String username;
	private String password;
	HashMap<String, String> h1 = new HashMap<>();

	public credentialss(String n, String p) {
		this.username = n;
		this.password = p;
	}

	public boolean verification() {

		boolean check = false;
		getdatafromDB();
		if (h1.containsKey(username)) {

			if (h1.get(username).equals(password)) {
				check = true;
			}

		}

		return check;
	}

	public void updateDB(String key, String user) {
		try {
			String url = "jdbc:postgresql://192.168.110.48:5432/plf_training";
			String username = "plf_training_admin";
			String password = "pff123";
			Class.forName("org.postgresql.Driver");

			Connection c = DriverManager.getConnection(url, username, password);
			PreparedStatement st = c.prepareStatement("update credentialss set key=? where username=?");
			st.setString(1, key);
			st.setString(2, user);

			st.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void getdatafromDB() {
		try {
			String url = "jdbc:postgresql://192.168.110.48:5432/plf_training";
			String username = "plf_training_admin";
			String password = "pff123";
			Class.forName("org.postgresql.Driver");

			Connection c = DriverManager.getConnection(url, username, password);
			Statement st = c.createStatement();
			ResultSet rs = st.executeQuery("select username,password from credentialss");

			while (rs.next()) {
				h1.put(rs.getString(1), rs.getString(2));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
