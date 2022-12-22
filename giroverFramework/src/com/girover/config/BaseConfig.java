package com.girover.config;

import java.util.HashMap;

public abstract class BaseConfig {
	
	private HashMap<String, String> configs = new HashMap<>();
	
	public abstract HashMap<String, String> get();
}
