package com.girover;

import java.sql.Connection;
import java.util.HashMap;

import com.girover.database.DB;
import com.girover.interfaces.ServiceProviderInterface;

import app.Config;

public class App {
	
	private static Config config;
	private static boolean isBooted = false;
	//           <AbstractClassName|interfaceName , className>
	private static HashMap<String, String> bindings;
	//                 <ClassName|interfaceName, Object>
	private static HashMap<String, Object> singeltones;
	
	public App(Config configInstance){
		
		config = configInstance;
		bindings = new HashMap<>();
		singeltones = new HashMap<>();
		
		boot();
	}
	
	public void boot() {
		if(isBooted) {
			return;
		}
		
		Env.load(config.ENVPath());
		
		bootAllServiceProviders();
		registerAllServiceProviders();
		
		isBooted = true;
	}
	
	private void bootIfNotBooted() {
		if(!isBooted)
			boot();
	}
	
	public void reBoot() {
		isBooted = false;
		boot();
	}
	
	private void generateDBConnection() {
		
		bootIfNotBooted();
		
		DB.generateConnection();
	}
	
	public Connection getDBConnection() {
//		if(dbConnection == null)
//			generateDBConnection();
		if(!DB.isConnected())
			DB.generateConnection();
		
		return DB.getConnection();
	}
	
	public void bootAllServiceProviders() {
		for (String className : config.serviceProviders()) {
			try {
				Class<?> clazz = Class.forName(className);
				clazz.getDeclaredMethod("boot").invoke(clazz.getDeclaredConstructor().newInstance());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public void registerAllServiceProviders() {
		for (String className : config.serviceProviders()) {
			try {
				Class<?> clazz = Class.forName(className);
				clazz.getDeclaredMethod("register").invoke(clazz.getDeclaredConstructor().newInstance());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void bind(String key, String className) {
		bindings.put(key, className);
	}
	
	public static void singeltone(String className, Object obj) {
		singeltones.put(className, obj);
	}
	
	public static Object make(String key) throws Exception {
		
		Object object = null;
		
		if(bindings.containsKey(key)) {
			Class<?> clazz = Class.forName(bindings.get(key));
			object = clazz.getDeclaredConstructor().newInstance();
		}
		
		return object;
	}
	
	public static Object resolve(String abstractName) {
		return getSingeltone(abstractName);
	}
	
	public static String getBind(String key){
		return bindings.get(key);
	}
	
	public static Object getSingeltone(String abstractName){
		
		if(singeltones.containsKey(abstractName))
			return singeltones.get(abstractName);
		
		return  null;
	}
	
	public static HashMap<String, Object> getSingeltones(){
		return  singeltones;
	}
	
	public static HashMap<String, String> getBinds(){
		return bindings;
	}
}
