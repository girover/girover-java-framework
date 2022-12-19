package app.models;

import com.girover.auth.Authenticatable;
import com.girover.database.eloquent.Model;

public class User extends Model implements Authenticatable {
	
	public User() {
		setTable("users");
		setPrimaryKey("userNumber");
	}

	public void method1() {
		System.out.println("method1 invoked from User class");
	}
}
