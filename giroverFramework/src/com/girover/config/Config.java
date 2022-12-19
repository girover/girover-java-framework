package com.girover.config;

import java.util.ArrayList;
import java.util.HashMap;

public class Config {
	
	private String configKey;
	private Config nestedConfig;
	private String[] configs;
	
	public Config(String configKey, String[] configs) {
		this.configKey = configKey;
		this.configs = configs;
	}
	
	public Config(String configKey, Config nestedConfig) {
		this.configKey = configKey;
		this.nestedConfig = nestedConfig;
	}
	
	public void setNestedConfig(Config config) {
		nestedConfig = config;
	}
	
	public void setConfigs(String[] configs) {
		this.configs = configs;
	}
}
