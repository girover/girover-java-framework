package com.girover.auth;

import com.girover.database.eloquent.EloquentBuilder;
import com.girover.database.eloquent.Model;

public class EloquentUserProvider implements UserProviderInterface {

	private static Model userModel;
	private static Class<? extends Model> userModelClass;
	private static String[] credentials = {"userName", "password"};
	
	
	public EloquentUserProvider(Class<? extends Model> modelClass) {
		userModelClass = modelClass;
	}
	
	public void setUserModel(Model model) {
		userModel = model;
	}

	public static void setUserAuthenticatingColumns(String...loginCredentials) {
		credentials = loginCredentials;
	}
	
	public Model retriveById(String id) {
		Model user = userModel;
		user = user.query().where(user.getPrimaryKey(), id).first();

		return user;
	}
	
	@Override
	public Model retriveByCredentials(String...loginCredentials) {
		
		if(userModel == null)
			userModel = createUserModel();

		EloquentBuilder query = userModel.newQuery();
		
		for (int i = 0; i< loginCredentials.length; i++) {
			query.where(credentials[i], loginCredentials[i]);
		}
		
		Model user = query.first();
		
		return user;
	}
	
	
	private Model createUserModel() {
		try {
			Model user = userModelClass.getDeclaredConstructor().newInstance();
			
			return user;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
}
