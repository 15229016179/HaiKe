package com.haike.web.util;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

/**
 * @author xiaoming
 *
 * @tags
 */
public class ConnectionFactory {

	private static Connection conn = null;
	private static String driver;
	private static String dburl;
	private static String user;
	private static String password;

	private static ConnectionFactory connectionFactory = new ConnectionFactory();

	static {
		Properties prop = new Properties();
		try {
			InputStream in = ConnectionFactory.class.getClassLoader()
					.getResourceAsStream("dbconfig.properties");
			prop.load(in);
		} catch (Exception e) {
			System.out.println("properties file read error");
		}

		driver = prop.getProperty("driver");
		dburl = prop.getProperty("dbUrl");
		user = prop.getProperty("user");
		password = prop.getProperty("password");
	}

	private ConnectionFactory() {

	}

	public Connection getConnection() {
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(dburl, user, password);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}

	public static ConnectionFactory getInstance() {
		return connectionFactory;
	}
}
