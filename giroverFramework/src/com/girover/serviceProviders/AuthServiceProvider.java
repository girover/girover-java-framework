package com.girover.serviceProviders;

import com.girover.auth.Auth;
import com.girover.auth.EloquentUserProvider;
import com.girover.interfaces.ServiceProviderInterface;

import app.models.User;

public class AuthServiceProvider implements ServiceProviderInterface {

	@Override
	public void boot() {
		System.out.println("Booting AuthServiceProvider...");
		
		EloquentUserProvider.setUserAuthenticatingColumns("userName", "password");
		Auth.setUserProvider(new EloquentUserProvider(User.class));
		
		System.out.println("AuthServiceProvider boot successfuly.");
	}

	@Override
	public void register() {
		
	}
}
