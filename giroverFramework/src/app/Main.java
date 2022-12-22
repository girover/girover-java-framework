package app;

import java.util.ArrayList;

import com.girover.App;
import com.girover.database.eloquent.Model;

import app.models.User;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

	@Override
	public void start(Stage primaryStage) {
		try {
//			QueryBuilder q = new QueryBuilder();
//			q.select("name","lastname");
//			String className = new Object(){}.getClass().getEnclosingClass().getName();
//		    System.out.println(className);
//			Student s1 = new Student();
//			Student s2 = new Student();
//			s1.query().where("d", "d");
//			
//			Object[] s3 = new Object[] {1,"majed", "1", s2};
//			for (Object object : s3) {
//				if(object.getClass().getSuperclass().equals(Model.class))
//				{
//					System.out.println(object.getClass()+" is : Model");
//				
//				}else {
//					System.out.println(object+" is not : Model");
//					
//				}
//			}
			
//			System.out.println(s3);
//			File xmlFile = new File("src/app.xml");
//			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
//			DocumentBuilder builder = factory.newDocumentBuilder();
//			Document doc = (Document) builder.parse(xmlFile);
//			Element el = doc.getDocumentElement();
//			System.out.println(el.getNodeType());
//			NodeList nodes = el.getChildNodes();
			
			
//			for (int i=0; i<nodes.getLength(); i++) {
//				System.out.println(nodes.item(i).getNodeType());
//			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		App app = new App(new Config());
		User user = new User();
		try {
			ArrayList<? extends Model> users =  user.query().get();
			for (Model user2 : users) {
				System.out.println((User)user2);
			}
			
			User majed = (User) user.query().find(1);
			
			System.out.println("first : ");
			System.out.println("count : "+users.size());
			System.out.println(users.removeIf((p)->p.getTable().equals("users")));
			System.out.println("count : "+users.size());
			
			System.out.println("Contains Majed : "+users.contains(majed));
			System.out.println("is Empty : "+users.isEmpty());
			users.clear();
			System.out.println("is Empty : "+users.isEmpty());
			
			for (String model : Model.modelEvents.keySet()) {
				System.out.println(Model.modelEvents.get(model));
				Model.modelEvents.get(model).get("deleted").fire(majed);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
//		c.add(new Student());
//		c.add(new User());
//		c.add(new Course());
		
//		System.out.println(c.size());
//		c.clear();
//		System.out.println(c.size());
//		primaryStage.show();
//		View view = new View("Login");
//		View view2 = new View("Login");
//		view.title("Login Page").show();
//		try {
//			Auth.attempt("majed", "4321");
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		Authenticatable u = new User();
//		u.login("userName","majed", "4321");
//		Query q = new Query();
//		for (Row row : q.select("select * from users where userNumber=? OR userNumber>?", 2,1)) {
//			System.out.println(row);
//		}
//		
////		System.out.println(app.getClass().getProtectionDomain().getCodeSource().getLocation().getPath());
//		Student student = new Student();
////		Student s = (Student)student.find(101);
//		Collection s = student.all();
//		System.out.println(s);
//		for (Model model : s) {
//			System.out.println(model.get("firstname"));
//		}
//		System.out.println(s.courses());
//		try {
//			Collection students = student.all();
//			for (Model s : students) {
//				System.out.println(s.get("firstname"));
//				for (Model model : ((Student)s).courses().get()) {
//					System.out.println(model.get("navn"));
//				}
//				System.out.println();
//			}
//		} catch (Exception e2) {
//			e2.printStackTrace();
//		}
//		Model user = new User();
//		Class cls = User.class;
//		
//		try {
//			System.out.println(App.getSingeltone(cls).getClass().getMethod("method1").invoke(App.getSingeltone(cls)));
//		} catch (Exception e3) {
//			// TODO Auto-generated catch block
//			e3.printStackTrace();
//		}
//		try {
//			System.out.println(App.make("user").toString());
//		} catch (Exception e2) {
//			e2.printStackTrace();
//		}
//		User u = (User) Auth.user();
//		u.login("majed","1234");
		
//		User majed = new User();
//		majed.login("majed","1234");
//		User juan = new User();
//		juan = (User)juan.query().find(2);
		try {
//			Auth.login("majed", "1234");
//			if(Auth.isAuthenticated())
//				System.out.println(Auth.user());
//			Auth.logout();
//			Auth.login(juan);
//			System.out.println("Primary key of autenticated user is : "+Auth.id());
//			System.out.println("UserName of autenticated user is : "+Auth.user().get("userName"));
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
//		System.out.println(Env.get("DB_DRIVER"));
//		System.out.println(Env.get("DB_NAME"));
//		System.out.println(app.getDBConnection());
//		
//		Student s = new Student();
//		try {
//			Student a = (Student)s.query().find(111);
//			for(Model m : a.courses().get()) {
//				System.out.println(m);
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		System.out.println(juan.exists());
//		juan.set("lastname", "Ibrahim");
//		juan.set("firstname", "Juannnnn");
//		System.out.println(juan.save());
//		s.setAttribute("firstname", "Juan");
//		juan.set("semester_no", 1);
//		s.setOriginal("lastname", "Khanjar");
//		s.setAttribute("semester_no", 3);
//		System.out.println(s.save());
//		try {
//			for (Model st : s.query().where("lastname", "Farhan").select("firstname", "lastname").get()) {
//				System.out.println(st);
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
	}

	public static void main(String[] arg0) {
		// TODO Auto-generated method stub
		launch(arg0);
	}

}
