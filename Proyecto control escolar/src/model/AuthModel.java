package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class AuthModel {
	
	public AuthModel() {
		
	}
	
	public boolean access(String email, String password) {
		boolean acceso = false;
		String query = "SELECT 1"
				+ "		  FROM Users"
				+ "	     WHERE Email=? AND Password=?";
	
		try (
				Connection con = ConexionBD.getConnection();
				PreparedStatement stmt = con.prepareStatement(query);
			) {
			stmt.setString(1, email);
			stmt.setString(2, password);

			try (ResultSet rs = stmt.executeQuery()) {
				acceso = rs.next();
				return acceso;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}
