package app.models;

import com.girover.database.eloquent.Model;
import com.girover.database.relationship.HasMany;

public class Student extends Model {
	public Student() {
		setTable("student");
		setPrimaryKey("id");
	}
	
	public HasMany courses() {
		return new HasMany(this, new Course(), "semester_no", "semester_no");
	}
}
