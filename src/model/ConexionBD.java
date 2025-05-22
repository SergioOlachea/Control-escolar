package model;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConexionBD {
	private static String URL;
	private static String USER;
	private static String PASS;
	private static Connection con;
	
	static {
		Properties props = new Properties();
		try (InputStream in = ConexionBD.class.getClassLoader().getResourceAsStream("database.properties")) {
			props.load(in);
			URL = props.getProperty("db.url");
			USER = props.getProperty("db.user");
			PASS = props.getProperty("db.pass");
		} catch (IOException e) {
			System.out.println("No se pudo cargar el archivo de configuración");
		}
	}
	
	public static Connection getConnection() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			try {
				con = DriverManager.getConnection(URL, USER, PASS);
			} catch (SQLException e) {
				System.out.println("No se pudo establecer la conexion"); 
			}
		} catch (ClassNotFoundException e) {
			System.out.println("No se encontro el driver"); 
		}
		return con;
	}
}
