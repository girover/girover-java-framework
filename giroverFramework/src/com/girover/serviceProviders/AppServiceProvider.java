package com.girover.serviceProviders;

import com.girover.interfaces.ServiceProviderInterface;

public class AppServiceProvider implements ServiceProviderInterface {

	@Override
	public void boot() {
		System.out.println("AppServiceProvider is booted...");
//		App.bind("user", User.class);
//		App.singeltone(User.class, new User());
	}

	@Override
	public void register() {
		
	}
}
