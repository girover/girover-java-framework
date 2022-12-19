package com.girover;

import java.sql.Connection;
import java.util.HashMap;

import com.girover.database.DB;

import app.Config;

public class App {
	
	private static Config config;
	private static boolean isBooted = false;
	private static HashMap<String, Class<?>> bindings;
	//                 Class,interface   object
	private static HashMap<Class<?>, Object> singeltones;
	
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
		for (Class<?> cls : config.serviceProviders()) {
			try {
				cls.getDeclaredMethod("boot").invoke(cls.getDeclaredConstructor().newInstance());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public void registerAllServiceProviders() {
		for (Class<?> cls : config.serviceProviders()) {
			try {
				cls.getDeclaredMethod("register").invoke(cls.getDeclaredConstructor().newInstance());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void bind(String key, Class<?> cls) {
		bindings.put(key, cls);
	}
	
	public static void singeltone(Class<?> cls, Object obj) {
		singeltones.put(cls, obj);
	}
	
	public static Object make(String key) throws Exception {
		Object o = null;
		if(bindings.containsKey(key)) {
			o = bindings.get(key).getDeclaredConstructor().newInstance();
		}
		return o;
	}
	
	public static Object resolve(Class<?> clazz) {
		return singeltones.get(clazz);
	}
	
	public static Class<?> getBind(String key){
		return bindings.get(key);
	}
	
	public static Object getSingeltone(Class<?> cls){
		return  cls.cast(singeltones.get(cls));
	}
	
	public static HashMap<Class<?>, Object> getSingeltones(){
		return  singeltones;
	}
	
	public static HashMap<String, Class<?>> getBinds(){
		return bindings;
	}
}
