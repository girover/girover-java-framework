package com.girover.database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

public class Row {

	protected HashMap<String, String[]> attributes = new HashMap<>();

	public void setAttributes(ResultSet res) {
		try {
			int columnCount = res.getMetaData().getColumnCount();
			for (int i = 1; i <= columnCount; i++) {
				String[] arr = { res.getString(i), res.getMetaData().getColumnTypeName(i) };
				attributes.put(res.getMetaData().getColumnLabel(i), arr);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void setAttribute(String key, String[] value) {
		this.attributes.put(key, value);
	}

	public String get(String key) {
		String[] arr = attributes.get(key);
		if (arr != null)
			return arr[0]; // get value from new entered values

		return null;
	}

	public String getType(String key) {
		String[] arr = attributes.get(key);
		if (arr != null)
			return arr[1]; // get type from new entered values

		return null;
	}

	public String toString() {
		
		String str = "";		
		
		int i = 0;
		int count = attributes.size();
		for (String column : this.attributes.keySet()) {
			str += String.format("%s : %s", column, attributes.get(column)[0]);
			
			if(i != count - 1)
				str += ", ";
			i++;
		}
		
		return "{" + str + "}";
	}
}
