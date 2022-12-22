package app.models;

import com.girover.auth.Authenticatable;
import com.girover.database.eloquent.Model;

public class User extends Model implements Authenticatable {
	static {
		System.out.println("User Model loaded -------");
		if(! Model.booted.contains(User.class.getName())){
			Model.booted.add(User.class.getName());
		}
		System.out.println("Booted Models : ");
		for (String string : Model.booted) {
			System.out.println(string);
		}
		
		
	}
	
	public User() {
		setTable("users");
		setPrimaryKey("userNumber");
//		HashMap<String, Consumer<? extends Model>> action = new HashMap<>();
//		Consumer<? extends Model> method = x -> x.query().where("lastname", "Farhan");
//		action.put("create", method);
//		bootedActions.put(User.class.getName(), action);
	}

	public void method1() {
		System.out.println("method1 invoked from User class");
	}
	
	
	public void boot() {
		addModelEvent(
			MODEL_EVENT_SAVED, 
			(model)->{
				model.query().where("lastname","farhan");
			}
		);
		addModelEvent(
			MODEL_EVENT_DELETED, 
			(model)->{
				System.out.println("the model : "+model.get("userName")+ " Deleted successfuly.");
			}
		);
	}
}
