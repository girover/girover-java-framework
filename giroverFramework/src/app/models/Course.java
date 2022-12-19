package app.models;

import com.girover.database.eloquent.Model;

public class Course extends Model {
	public Course() {
		setTable("course");
		setPrimaryKey("id");
	}
}
