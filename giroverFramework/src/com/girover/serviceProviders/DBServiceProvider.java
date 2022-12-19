package com.girover.serviceProviders;

import com.girover.App;
import com.girover.Env;
import com.girover.database.DB;
import com.girover.database.DBConnection;
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
		
		App.singeltone(DBConnection.class, new SqlServerConnection());
		DB.generateConnection();
	}

	@Override
	public void register() {

		Model.setConnection(DB.getConnection());
		Query.setConnection(DB.getConnection());
	}
}
