package com.girover.serviceProviders;

import com.girover.App;
import com.girover.auth.Auth;
import com.girover.auth.EloquentUserProvider;
import com.girover.auth.UserProviderInterface;
import com.girover.interfaces.ServiceProviderInterface;

import app.models.User;

public class AuthServiceProvider implements ServiceProviderInterface {

	@Override
	public void boot() {
		System.out.println("Booting AuthServiceProvider...");
		
		App.bind(UserProviderInterface.class.getName(), EloquentUserProvider.class.getName());
		
		System.out.println("AuthServiceProvider boot successfuly.");
	}

	@Override
	public void register() {
		System.out.println("Registring AuthServiceProvider...");
		
		EloquentUserProvider.setUserAuthenticatingColumns("userName", "password");
//		Auth.setUserProvider(new EloquentUserProvider(User.class));

		System.out.println("AuthServiceProvider registered successfuly.");
	}
}
