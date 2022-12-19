package com.girover;

import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Properties;

public class Env {
	static HashMap<String, String> envs = new HashMap<>();
	
	
	public static void set(String key, String value) {
		envs.put(key, value);
	}
	
	public static String get(String key) {
		return envs.get(key);
	}
	
	public static HashMap<String, String> getAllEnvs() {
		return envs;
	}
	
	public static void load(String envFilePath) {
		loadEnvsFromPropertiesFile(envFilePath);
	}
	
	private static void loadEnvsFromPropertiesFile(String envFilePath) {
		try {
			
			FileInputStream propsInput = new FileInputStream(envFilePath);
			Properties prop = new Properties();
			prop.load(propsInput);

			for(Object key : prop.keySet()) {
				
				envs.put((String)key, prop.getProperty((String)key));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void refreshEnvs() {
		envs.clear();
		
		loadEnvsFromPropertiesFile(null);
	}
	
	public static Env getInstance() {
		return new Env();
	}
}
