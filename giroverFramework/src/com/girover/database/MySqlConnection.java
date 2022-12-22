package com.girover.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySqlConnection implements DBConnectionInterface {

	protected String host = "127.0.0.1";
	protected String port = "3306";
	protected String user = "root";
	protected String password = "";
	protected String dbName = "torino_pizza"; 
	
	private static Connection connection;
	
	@Override
	public Connection getConnection() {

			try {
				if(connection == null) {
					Class.forName("com.mysql.cj.jdbc.Driver");					
					connection = DriverManager.getConnection(generateConnectionUrl(), user, password);
				}
			} catch (Exception e) {				
				e.printStackTrace();
			}
		
		return connection;
	}
	
	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getDbName() {
		return dbName;
	}

	public void setDbName(String dbName) {
		this.dbName = dbName;
	}

	private String generateConnectionUrl() {
		return String.format("jdbc:mysql://%s:%s/%s", host, port, dbName);
	}

}
