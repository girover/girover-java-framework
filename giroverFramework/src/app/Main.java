package app;

import java.util.ArrayList;

import com.girover.App;
import com.girover.database.eloquent.Model;
import com.girover.view.View;

import app.models.Student;
import app.models.User;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

	@Override
	public void start(Stage primaryStage) {
		try {
			App app = new App(new Config());
			app.run();
			
			testSavingUser();
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.exit(0);
	}

	public static void main(String[] arg0) {
		// TODO Auto-generated method stub
		launch(arg0);
	}

	public static void testModelQueryBuilder() {
		User user = new User();
		
		
		System.out.println(user.find(1));
	}
	
	public static void testModelCollection() throws Exception{
		Student student = new Student();
		
		ArrayList<? extends Model> students = student.query().where("lastname", "Farhan").get();
		
		students.forEach(s -> System.out.println(s));
	}
	
	public static void testView() {
		View view = new View("Login");
		view.show();
	}
	
	public static void testSavingUser() {
		User user = new User();
		user.set("userName", "ahmed");
		user.set("password", "54321");
		user.save();
	}
}
