package com.girover.database.eloquent;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.girover.database.DB;
import com.girover.database.QueryBuilder;

//import application.App;

public abstract class Model {

	// The name of table that holds all data about the model.
	protected String table;
	protected String primaryKey = "id";
	protected String primaryKeyType = "int";
	protected String foreignKey;
	// Determine if the current model exists in the database or not.
	protected boolean exists = false;
	// The data of model as they exist in the database.
	protected HashMap<String, String[]> originalAttributes = new HashMap<>();
	// When set new values to the model before updating the model in the database.
	protected HashMap<String, String[]> dirtyAttributes = new HashMap<>();
	protected static Connection dbConnection;
	
	protected EloquentBuilder query;

	public Model() {
	
	}

	public static void setConnection(Connection dbconnection) {
		dbConnection = dbconnection;
	}

	public Connection getConnection() {
		if(dbConnection == null)
			dbConnection = DB.getConnection();
		
		return dbConnection;
	}
	
	public Model find(int id) {
		return this.find(Integer.toString(id));
	}
	
	public Model find(String id) {
		return this.query().where(this.getPrimaryKey(), id).first();
	}

	public Collection all() {

		try {
			return this.query().where("1", "1").get();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

	public void setExist(boolean exist) {
		exists = exist;
	}

	public Model save() {
		 if(this.exists) {
			 return saveExistedModel();
		 }else {
			 return saveNewModel();
		 }
	}

	private Model saveNewModel() {
		return query().insertModel();
	}

	private Model saveExistedModel() {
		return query().updateCurrentModel();
	}
	
	public Model delete() {
		return query().deleteCurrentModel();
	}

	public String getTable() {
		return table;
	}

	public void setTable(String table) {
		this.table = table;
	}
	
	public String getQualifiedColumn(String column) {
		return this.getTable() + "." + column;
	}

	public String getPrimaryKey() {
		return primaryKey;
	}
	
	public String getQualifiedPrimaryKey() {
		return getQualifiedColumn(primaryKey);
	}
	
	public String getPrimaryKeyValue() {
		if(originalAttributes.containsKey(getPrimaryKey()))
			return originalAttributes.get(getPrimaryKey())[0];
		
		return null;
	}

	public void setPrimaryKey(String primaryKey) {
		this.primaryKey = primaryKey;
	}

	public String getForeignKey() {
		return foreignKey;
	}

	public void setForeignKey(String foreignKey) {
		this.foreignKey = foreignKey;
	}

	public void setOriginalAttribute(String key, String[] value) {
		this.originalAttributes.put(key, value);
	}
	
	public void setOriginal(String key, String value) {
		String[] arr = {value, "String"};
		setOriginalAttribute(key, arr);
	}
	
	public void setOriginal(String key, int value) {
		String[] arr = {Integer.toString(value), "Integer"};
		setOriginalAttribute(key, arr);
	}
	
	public void setOriginal(String key, Date value) {
		String[] arr = {value.toString(), "Date"};
		setOriginalAttribute(key, arr);
	}
	
	public void setOriginal(String key, Timestamp value) {
		String[] arr = {value.toString(), "Timestamp"};
		setOriginalAttribute(key, arr);
	}
	
	public void setOriginal(String key, Double value) {
		String[] arr = {value.toString(), "Double"};
		setOriginalAttribute(key, arr);
	}
	
	public void setOriginal(String key, Float value) {
		String[] arr = {value.toString(), "Float"};
		setOriginalAttribute(key, arr);
	}
	
	public void set(String key, String[] value) {
		this.dirtyAttributes.put(key, value);
	}
	
	public void set(String key, String value) {
		String[] arr = {value, "string"};
		set(key, arr);
	}
	
	public void set(String key, int value) {
		String[] arr = {Integer.toString(value), "int"};
		set(key, arr);
	}
	
	public void set(String key, Date value) {
		String[] arr = {value.toString(), "date"};
		set(key, arr);
	}
	
	public void set(String key, Timestamp value) {
		String[] arr = {value.toString(), "timestamp"};
		set(key, arr);
	}
	
	public void set(String key, Double value) {
		String[] arr = {value.toString(), "double"};
		set(key, arr);
	}
	
	public void set(String key, Float value) {
		String[] arr = {value.toString(), "float"};
		set(key, arr);
	}

	public Map<String, String[]> getOriginalAttributes() {
		return originalAttributes;
	}
	
	public Map<String, String[]> getDirtyAttributes() {
		return dirtyAttributes;
	}

	public void setAttributes(HashMap<String, String[]> attributes) {
		this.originalAttributes = attributes;
	}

	public void setOriginalAttributes(ResultSet res) {
		try {
			int columnCount = res.getMetaData().getColumnCount();
			for (int i = 1; i <= columnCount; i++) {
				String[] arr = {res.getString(i), res.getMetaData().getColumnTypeName(i)};
				originalAttributes.put(res.getMetaData().getColumnLabel(i), arr);
			}
			setExist(true);

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public boolean exists() {
		return exists;
	}

//	public void fill(ArrayList<String> attributes) {
//		for (String attr : attributes) {
//			this.originalAttributes.put(attr, "");
//		}
//	}

	public EloquentBuilder query() {
		
		if(query == null)
			query = new EloquentBuilder(this, new QueryBuilder());
		
		return query;
	}
	
	public EloquentBuilder newQuery() {
		
		return query = new EloquentBuilder(this, new QueryBuilder());
	}

	public String get(String key) {
		String[] arr = dirtyAttributes.get(key);
		if(arr != null)
			return arr[0]; // get value from new entered values
		
		arr = originalAttributes.get(key);
		if(arr != null)
			return arr[0]; // get value from original values [DB values]
		
		return null;
	}
	
	public String[] getWithType(String key) {
		String[] arr = dirtyAttributes.get(key);
		if(arr != null)
			return arr; // get value from new entered values
		
		arr = originalAttributes.get(key);
		if(arr != null)
			return arr; // get value from original values [DB values]
		
		return null;
	}

	public String toString() {
		String str = "";

		for (String key : originalAttributes.keySet()) {
			str += key + " : " + originalAttributes.get(key)[0] + "\n";
		}

		return str;
	}
}
