package com.girover.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.girover.Env;

public class SqlServerConnection implements DBConnection {

	private String host = "localhost";
	private String port = "1433";
	private String instanceName = "SQLEXPRESS";
	private String databaseName = "Skole"; 
	private String user = "";
	private String password = "";
	private boolean integratedSecurity = true; 
	private boolean trustServerCertificate = true; 
	
	private static Connection connection;
	
	public SqlServerConnection() {

		prepearAttributes();
	}
	
	private void prepearAttributes() {
		String envHost = Env.get("DB_HOST");
		if(envHost != null)
			host = envHost.equals("") ? host : envHost;
		
		String envPort = Env.get("DB_PORT");
		if(envPort != null)
			port = envPort.equals("") ? port : envPort;
		
		String envInstanceName = Env.get("DB_INSTANCE_NAME");
		if(envInstanceName != null)
			instanceName = envInstanceName.equals("") ? instanceName : envInstanceName;
		
		String envDbName = Env.get("DB_NAME");
		if(envDbName != null)
			databaseName = envDbName.equals("") ? databaseName : envDbName;
		
		String envUser = Env.get("DB_USER");
		if(envUser != null)
			user = envUser.equals("") ? user : envUser;
		
		String envPassword = Env.get("DB_PASSWORD");
		if(envPassword != null)
			password = envPassword.equals("") ? password : envPassword;
		
		String envIntegrate = Env.get("DB_INTEGRATED_SECURITY");
		if(envIntegrate != null)
			integratedSecurity = envIntegrate.equals("") ? integratedSecurity : Boolean.parseBoolean(envIntegrate);
		
		String envTrustServerCertificate = Env.get("DB_TRUST_SERVER_CERTIFICATE");
		if(envTrustServerCertificate != null)
			integratedSecurity = envTrustServerCertificate.equals("") ? integratedSecurity : Boolean.parseBoolean(envTrustServerCertificate);
	}
	
	@Override
	public Connection getConnection() {

			try {
				if(connection == null) {
					System.out.println("Loading SqlServe JDBC ...");
					Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");					
					connection = DriverManager.getConnection(generateConnectionUrl());
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
		return databaseName;
	}

	public void setDbName(String dbName) {
		this.databaseName = dbName;
	}

	private String generateConnectionUrl() {
		
		String url = "jdbc:sqlserver://";
		
		url += String.format("%s:%s;", this.host, this.port);
		
		if(!this.instanceName.isEmpty()) {
			url += String.format("instanceName=%s;", this.instanceName);
		}
		
		if(!this.databaseName.isEmpty()) {
			url += String.format("databaseName=%s;", this.databaseName);
		}
		
		if(this.trustServerCertificate) {
			url += String.format("trustServerCertificate=%s;", "true");
		}
		
		if(this.integratedSecurity) {
			url += String.format("integratedSecurity=%s;", "true");
		}
	
		return url;
	}

}
