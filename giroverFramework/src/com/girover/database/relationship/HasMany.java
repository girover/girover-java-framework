package com.girover.database.relationship;

import com.girover.database.eloquent.Collection;
import com.girover.database.eloquent.Model;

public class HasMany {
	
	private Model one, many;
	private String foreignKey, primaryKey;
	
	public HasMany(Model one, Model many){
		this.one = one;
		this.many = many;
		
		this.setKeys(one.getForeignKey(), many.getPrimaryKey());
	}
	
	
	public HasMany(Model one, Model many, String foreignKey, String localKey){
		this.one = one;
		this.many = many;
		
		this.setKeys(foreignKey, localKey);
	}
	
	
	private void setKeys(String foreignKey, String primaryKey) {
		this.foreignKey = foreignKey;
		this.primaryKey = primaryKey;
	}
	
	public Collection get() {
		String onePrimaryKey = one.get(primaryKey);
		try {
			return many.query().where(foreignKey, onePrimaryKey).get();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
