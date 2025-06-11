package model;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class ConexionBD {
	private static String URL;
	private static String USER;
	private static String PASS;
	private static HikariDataSource dataSource;
	
	static {
		Properties props = new Properties();
		try (InputStream in = ConexionBD.class.getClassLoader().getResourceAsStream("database.properties")) {
			props.load(in);
			HikariConfig config = new HikariConfig();			
			URL = props.getProperty("db.url");
			USER = props.getProperty("db.user");
			PASS = props.getProperty("db.pass");
            config.setJdbcUrl(URL);
            config.setUsername(USER);
            config.setPassword(PASS);

            config.setMaximumPoolSize(5);
            config.setMinimumIdle(2);
            config.setIdleTimeout(60000); 
            config.setConnectionTimeout(30000);
            config.setDriverClassName("com.mysql.cj.jdbc.Driver");

            dataSource = new HikariDataSource(config);
		} catch (IOException e) {
			System.out.println("No se pudo cargar el archivo de configuración");
		}
	}
	
	public static Connection getConnection() throws SQLException {
        if (dataSource != null) {
            return dataSource.getConnection();
        } else {
        	throw new SQLException();
        }
	}
}
