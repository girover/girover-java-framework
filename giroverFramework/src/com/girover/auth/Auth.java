package com.girover.auth;

import com.girover.App;
import com.girover.database.eloquent.Model;

public class Auth {
	
	// This will be executed only once
	// When the class is loaded first time in memory.
	static {
		try {
			setUserProvider((UserProviderInterface)App.make(UserProviderInterface.class.getName()));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static UserProviderInterface userProvider;
	private static Object authenticatedUser;

	public static void setUserProvider(UserProviderInterface provider) {
		userProvider = provider;
	}

	public static Object getAuthenticatedUser() {

		return authenticatedUser;
	}

	public static void login(String...credentials) throws Exception {
//		authenticatingModel.getClass()
//						   .getMethod("login")
//						   .invoke(authenticatingModel, credentials[0], credentials[1]);
	}
	
	public static boolean attempt(String...credentials) throws Exception {
		Object user = userProvider.retriveByCredentials(credentials);
		
		if(user != null) {
			authenticatedUser = user;
				
			return true;
		}
		
		return false;
	}
	
	/**
	 * Login existed User Model to the system
	 * 
	 * @param authenticatableModel
	 * @throws Exception
	 */
	public static boolean login(Model authenticatableModel) throws Exception {
		
			if(authenticatableModel.exists()) {
				authenticatedUser = authenticatableModel;
				
				return true;
			}
			
			return false;
	}

	public static void logout() {
		authenticatedUser = null;
	}

	public static Object user() {
		return authenticatedUser;
	}

	public static boolean isAuthenticated() {
		if (authenticatedUser == null)
			return false;

		return true;
	}
	
	public static String id() {
		if(Model.class.isAssignableFrom(authenticatedUser.getClass())) {
			return ((Model)authenticatedUser).getPrimaryKeyValue();
		}
		
		return null;
	}
}
