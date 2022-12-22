package app.models;

import com.girover.auth.Authenticatable;
import com.girover.database.eloquent.Model;

public class User extends Model implements Authenticatable {
	
	public User() {
		setTable("users");
		setPrimaryKey("userNumber");
	}
	
	public void boot() {
		
		super.boot();
		
		saving(model -> model.set("userName", model.get("userName").toUpperCase()));
		
		saved(model -> model.query().where("lastname","farhan"));
		
		deleted(model -> System.out.println("the model : " + model.get("userName")+ " Deleted successfuly."));
	}
}
