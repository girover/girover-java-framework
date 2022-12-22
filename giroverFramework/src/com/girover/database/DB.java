package com.girover.database;

import java.sql.Connection;

import com.girover.App;

public class DB {
	private static Connection connection;

	public static void setConnection(Connection con) {
		connection = con;
	}

	public static Connection getConnection() {
		if(connection == null)
			generateConnection();
		
		return connection;
	}
	
	public static boolean isConnected() {
		return connection == null ? false : true;
	}

	public static void generateConnection() {
		
		setConnection(generateNewConnection());
	}
	
	public static Connection generateNewConnection() {

		DBConnectionInterface DBCon = (DBConnectionInterface) App.resolve(DBConnectionInterface.class.getName());
		
		return DBCon != null ? DBCon.getConnection() : null;
	}
}
