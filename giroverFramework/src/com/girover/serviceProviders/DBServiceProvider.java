package com.girover.serviceProviders;

import com.girover.App;
import com.girover.Env;
import com.girover.database.DB;
import com.girover.database.DBConnectionInterface;
import com.girover.database.Query;
import com.girover.database.SqlServerConnection;
import com.girover.database.eloquent.Model;
import com.girover.interfaces.ServiceProviderInterface;

public class DBServiceProvider implements ServiceProviderInterface {

	@Override
	public void boot() {
		System.out.println("Booting DBServiceProvider...");
		
		Env.set("DB_DRIVER", "SqlServer");
		Env.set("DB_HOST", "localhost");
		Env.set("DB_PORT", "1433");
		Env.set("DB_INSTANCE_NAME", "SQLEXPRESS");
		Env.set("DB_NAME", "SkoleDB");
		Env.set("DB_INTEGRATED_SECURITY", "true");
		Env.set("DB_TRUST_SERVER_CERTIFICATE", "true");
		
		// Store an instance of database connection in application container
		App.singeltone(DBConnectionInterface.class.getName(), new SqlServerConnection());
		// Store class name of database connection in application container
		// to make new instances of it when needed.
		App.bind(DBConnectionInterface.class.getName(), SqlServerConnection.class.getName());
		
		
		
		System.out.println("DBServiceProvider is booted.");
	}

	@Override
	public void register() {
		System.out.println("Registring database services....");
		
		// Generating database connection from application container.
		DB.generateConnection();
		// Connect Model Class with database using stored connection instance.
		Model.setConnection(DB.getConnection());
		// Connect Query Builder with database using stored connection instance.
		Query.setConnection(DB.getConnection());

		System.out.println("Database services registered successfuly");
	}
}
