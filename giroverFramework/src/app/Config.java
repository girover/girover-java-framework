package app;

public class Config {

	/*
	 * Here we store all service providers classes to boot when making new instance of
	 * com.girover.App
	 */
	public Class<?>[] serviceProviders() {

		// Add all Service Providers you want to boot here.
		Class<?>[] services = 
			{ 
				com.girover.serviceProviders.AppServiceProvider.class,
				com.girover.serviceProviders.DBServiceProvider.class,
				com.girover.serviceProviders.AuthServiceProvider.class ,
				com.girover.serviceProviders.ViewServiceProvider.class 
			};

		return services;
	}
	
	public String basePath() {
		return "src";
	}
	
	/**
	 * Here we provide the path to the "env.properties" file.
	 * @return
	 */
	public String ENVPath() {
		
		return this.basePath() + "/env.properties";
	}
}
