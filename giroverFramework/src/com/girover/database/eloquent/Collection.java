package com.girover.database.eloquent;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

public class Collection implements Iterable<Model> {

	private ArrayList<Model> rows = new ArrayList<>();

	public Collection(Model model, ResultSet result) {

		try {
			while (result.next()) {
				Model m;
				m = model.getClass().getDeclaredConstructor().newInstance();
				m.setOriginalAttributes(result);
				rows.add(m);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void add() {

	}

	public ArrayList<Model> items() {
		return rows;
	}

	public int size() {
		return rows.size();
	}
	
	public int count() {
		return rows.size();
	}
	
	public Model getFirstItem() {
		if(rows.size() > 0)
			return rows.get(0);
		return null;
	}

	@Override
	public Iterator<Model> iterator() {
		return rows.iterator();
	}
}
