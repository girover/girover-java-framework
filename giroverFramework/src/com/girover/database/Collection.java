package com.girover.database;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Iterator;

public class Collection implements Iterable<Row> {

	private ArrayList<Row> rows = new ArrayList<>();

	public Collection(ResultSet result) {

		try {
			while (result.next()) {
				Row row = new Row();
				row.setAttributes(result);
				rows.add(row);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void add() {

	}

	public ArrayList<Row> rows() {
		return rows;
	}

	public int size() {
		return rows.size();
	}
	
	public int count() {
		return rows.size();
	}
	
	public Row getFirstItem() {
		if(rows.size() > 0)
			return rows.get(0);
		return null;
	}

	@Override
	public Iterator<Row> iterator() {
		return rows.iterator();
	}
}
