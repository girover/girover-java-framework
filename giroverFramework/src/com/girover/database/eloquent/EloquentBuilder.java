package com.girover.database.eloquent;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.girover.database.QueryBuilder;

import app.models.User;

public class EloquentBuilder {

	private Model model;
	private Connection dbConnection;
	private QueryBuilder queryBuilder;
	/**
	 * wheres = {
	 * 		"type"     = "basic",   [basic|nested]
	 * 		"column"   = "email",
	 * 		"operator" = "=",
	 * 		"value"    = "email@email.com", 
	 * 		"boolean"  = "AND",     [AND | OR]
	 * }
	 */
	private ArrayList<String[]> wheres = new ArrayList<>();
	private ArrayList<String[]> orWheres = new ArrayList<>();
	private ArrayList<String> selects = new ArrayList<>();
	private ArrayList<? extends Model> arrayList;

	public EloquentBuilder(Model model, QueryBuilder query) {
		super();
		this.model = model;
		this.queryBuilder = query;
		this.dbConnection = model.getConnection();
	}

	public Model find(int id) {

		try {
			String sql = "SELECT * FROM " + model.getTable() + " WHERE " + model.getPrimaryKey() + " = ?;";

			PreparedStatement stm;

			stm = dbConnection.prepareStatement(sql);
			stm.setInt(1, id);

			ResultSet res = stm.executeQuery();

			while (res.next()) {
				Model newModel = model.getClass().getDeclaredConstructor().newInstance();
				newModel.setOriginalAttributes(res);
				return newModel;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	public Model first() {
		try {
			ArrayList<? extends Model> result = this.get();
			if (result.size() > 0)
				return result.get(0);

			return null;

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

	public Collection all() throws Exception {

		String sql = "SELECT * FROM " + model.getTable() + ";";
		ResultSet result = dbConnection.createStatement().executeQuery(sql);

		return new Collection(model, result);
	}

//
	public ArrayList<? extends Model> get() throws Exception {
		ResultSet result = dbConnection.createStatement().executeQuery(toSql());

		return parseModels(result);
//		return new Collection(model, result);
	}
	
	private ArrayList<? extends Model> parseModels(ResultSet resultSet){
		
		ArrayList<Model> models = new ArrayList<>();
		try {
			
			while (resultSet.next()) {
				Model model = this.model.getClass().getDeclaredConstructor().newInstance();
				model.setOriginalAttributes(resultSet);
				models.add(model);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return models;
	}

//	public void select(String[] fields) {
//		for(String field:fields) {
//			selects.add(field);
//		}
//	}
	public EloquentBuilder select(String... fields) {
		for (String field : fields) {
			selects.add(field);
		}

		return this;
	}

	private String compileSelects() {

		if (selects.size() == 0) {
			return " * ";
		}

		StringBuilder selectString = new StringBuilder();

		for (String select : selects) {
			selectString.append(select).append(",");
		}

		selectString.deleteCharAt(selectString.length() - 1);

		return selectString.toString();
	}

	public EloquentBuilder where(String key, String value) {
		return where(key, "=", value);
	}

	public EloquentBuilder where(String key, String operator, String value) {
		String[] s = { key, operator, value };
		wheres.add(s);

		return this;
	}

	private String compileWheres() {

		if (wheres.size() == 0) {
			return " ";
		}

		StringBuilder str = new StringBuilder();
		str.append(" WHERE ");
		int counter = 0;
		for (String[] where : wheres) {
			counter++;
			str.append(" " + where[0]).append(" " + where[1] + " ").append(" '" + where[2] + "' ");
			if (counter > 0 && counter < wheres.size()) {
				str.append(" AND ");
			}
		}

		return str.toString();
	}

	public EloquentBuilder orWhere(String key, String value) {
		return orWhere(key, "=", value);
	}

	public EloquentBuilder orWhere(String key, String operator, String value) {
		String[] s = { key, operator, value };
		orWheres.add(s);

		return this;
	}

	private String compileOrWheres() {

		if (orWheres.size() == 0) {
			return " ";
		}

		StringBuilder str = new StringBuilder();
		str.append(" OR ");
		int counter = 0;
		for (String[] where : orWheres) {
			counter++;
			str.append(" " + where[0]).append(" " + where[1] + " ").append(" '" + where[2] + "' ");
			if (counter > 0 && counter < wheres.size()) {
				str.append(" AND ");
			}
		}

		return str.toString();
	}

	public int max(String key) {
		return 1;
	}

	public Model insertModel() {
		try {
			Statement stm = dbConnection.createStatement();
			int affectedRows = stm.executeUpdate(generateInsertModelSql());

			if (affectedRows == 1) {
				this.model.setExist(true);
				ResultSet resultSet = stm.executeQuery("SELECT SCOPE_IDENTITY()");
				if (resultSet.next()) {
					String pkType = resultSet.getMetaData().getColumnTypeName(1);
					String[] arr = { resultSet.getString(1), pkType };
					this.model.setOriginalAttribute(this.model.getPrimaryKey(), arr);
				}
				return this.model;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	public Model updateCurrentModel() {
		try {
			Statement stm = dbConnection.createStatement();
			int affectedRows = stm.executeUpdate(generateUpdateModelSql());

			return this.model;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	public Model deleteCurrentModel() {
		try {
			Statement stm = dbConnection.createStatement();
			int affectedRows = stm.executeUpdate(generateDeleteModelSql());

			if(affectedRows > 0) {
				this.model.setExist(false);
				this.model.dirtyAttributes = this.model.originalAttributes;
				this.model.originalAttributes.clear();
			}
			return this.model;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	private String generateInsertModelSql() {
		String sql = "INSERT INTO " + this.model.getTable() + " ( ";

		int count = this.model.getOriginalAttributes().keySet().size();
		int i = 0;
		for (String attr : this.model.getOriginalAttributes().keySet()) {
			i++;
			sql += attr;
			sql += i == count ? ") VALUES (" : ", ";
		}
		i = 0;
		for (String attr : this.model.getOriginalAttributes().keySet()) {
			i++;
			sql += "'" + this.model.get(attr);
			sql += i == count ? "') " : "', ";
		}

		return sql;
	}

	private String generateUpdateModelSql() {
		String sql = "UPDATE " + this.model.getTable() + " SET ";

		int count = this.model.getDirtyAttributes().keySet().size();
		int i = 0;
		for (String attr : this.model.getDirtyAttributes().keySet()) {
			i++;
			if (attr.equals(this.model.getPrimaryKey()))
				continue;
			sql += attr + "=" + convertDirtyAttributeToString(this.model.getWithType(attr));
			sql += i == count ? " " : ", ";
		}
		sql += " WHERE " + this.model.getPrimaryKey() + " = " + this.model.getPrimaryKeyValue();

		return sql;
	}

	private String generateDeleteModelSql() {
		String sql = "DELETE FROM " + this.model.getTable() + " WHERE ";

		sql += this.model.getPrimaryKey() + " = " + this.model.getPrimaryKeyValue();

		return sql;
	}

	private String convertDirtyAttributeToString(String[] attr) {

		switch (attr[1]) {
		case "int", "float", "double", "timestamp": {
			return attr[0];
		}
		case "string", "date", "datetime": {
			return "'" + attr[0] + "'";
		}
		default:
			return "";
		}

	}

	private String generateSql() {
//		sql = "SELECT " + getSelectsForSql() + " FROM " + model.getTable() + " ";
		return "SELECT " + compileSelects() + " FROM " + model.getTable() + " " + compileWheres()
				+ compileOrWheres();
	}

	public String toSql() {
		return generateSql();
	}

}
