package app;

public class Config {

	/*
	 * Here we store all service providers classes to boot when making new instance of
	 * com.girover.App
	 */
	public String[] serviceProviders() {

		// Add all Service Providers you want to boot here.
		String[] serviceProvidersClasses = 
			{ 
				com.girover.serviceProviders.AppServiceProvider.class.getName(),
				com.girover.serviceProviders.DBServiceProvider.class.getName(),
				com.girover.serviceProviders.AuthServiceProvider.class.getName(),
				com.girover.serviceProviders.ViewServiceProvider.class.getName() 
			};

		return serviceProvidersClasses;
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
