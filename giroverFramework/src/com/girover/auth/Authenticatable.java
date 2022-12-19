package com.girover.auth;

public interface Authenticatable {

	public default void login(String...credentials) {

		try {
//			Method m = getClass().getMethod("query");
//			EloquentBuilder q = (EloquentBuilder)m.invoke(this);
//			
//			Model authenticatedUser = q.where(Auth.getUserNameField(), credentials[0])
//									   .where(Auth.getPasswordField(), credentials[1])
//									   .first();
//			if(authenticatedUser != null) {
//				Auth.setAuthenticatable(authenticatedUser);
//			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
}
