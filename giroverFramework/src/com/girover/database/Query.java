package com.girover.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

public class Query {

	private static Connection connection;
	
	public Query(){
		
	}
	
	public Collection select(String sql, Object...bindings) {
		try {
			PreparedStatement stm = connection.prepareStatement(sql);
			bindValues(stm, bindings);
			
			return new Collection(stm.executeQuery());
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public int update(String sql, Object...bindings) {
		try {
			PreparedStatement stm = connection.prepareStatement(sql);
			bindValues(stm, bindings);
			int affectedRows = stm.executeUpdate();
			
			return affectedRows;
		} catch (Exception e) {
			return -1;
		}
	}
	
	private void bindValues(PreparedStatement stm, Object...bindings) {
		if(bindings == null)
			return;
		
		int i = 0;
		for (Object binding : bindings) {
			i++;
			bind(stm, binding, i);
		}
		
	}
	
	private void bind(PreparedStatement stm, Object binding, int index) {
		
		try {
			String type = binding.getClass().toString();
			
			if(type.equals(Integer.class.toString())) {
				stm.setInt(index, (int)binding);
				return;
			}
			
			if(type.equals(Double.class.toString())) {
				stm.setDouble(index, (double)binding);
				return;
			}
			
			if(type.equals(Float.class.toString())) {
				stm.setFloat(index, (float)binding);
				return;
			}
			
			if(type.equals(String.class.toString())) {
				stm.setString(index, (String)binding);
				return;
			}
			
			if(type.equals(Integer.class.toString())) {
				stm.setInt(index, (int)binding);
				return;
			}
			
			if(type.equals(Boolean.class.toString())) {
				stm.setBoolean(index, (boolean)binding);
				return;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void setConnection(Connection dbConnection) {
		connection = dbConnection;
	}
}
