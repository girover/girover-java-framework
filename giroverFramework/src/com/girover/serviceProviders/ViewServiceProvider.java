package com.girover.serviceProviders;

import com.girover.interfaces.ServiceProviderInterface;
import com.girover.view.View;

public class ViewServiceProvider implements ServiceProviderInterface {

	@Override
	public void boot() {
		View.setFxmlPath("/views");
	}

	@Override
	public void register() {
		// TODO Auto-generated method stub
		
	}

}
