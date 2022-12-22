package com.girover.database.eloquent;

public class ModelEvent {
	
	private Model model;

	protected static final String MODEL_EVENT_CREATING  = "creating";
	protected static final String MODEL_EVENT_CREATED   = "created";
	protected static final String MODEL_EVENT_UPDATING  = "updating";
	protected static final String MODEL_EVENT_UPDATED   = "updated";
	protected static final String MODEL_EVENT_DELETING  = "deleting";
	protected static final String MODEL_EVENT_DELETED   = "deleted";
	protected static final String MODEL_EVENT_SAVING    = "saving";
	protected static final String MODEL_EVENT_SAVED     = "saved";
	protected static final String MODEL_EVENT_RESTORING = "restoring";
	protected static final String MODEL_EVENT_RESTORED  = "restored";
	
	public ModelEvent(Model model) {
		this.model = model;
	}
	
	private static void registerModelEvent(String eventName, ModelEventInterface callback) {
		
	}
	
	protected static void creating(ModelEventInterface callback) {
		registerModelEvent(MODEL_EVENT_CREATING, callback);
	}
	
	protected static void created(ModelEventInterface callback) {
		registerModelEvent(MODEL_EVENT_CREATED, callback);
	}
	
	protected static void updating(ModelEventInterface callback) {
		registerModelEvent(MODEL_EVENT_UPDATING, callback);
	}
	
	protected static void updated(ModelEventInterface callback) {
		registerModelEvent(MODEL_EVENT_UPDATED, callback);
	}
	
	protected static void deleting(ModelEventInterface callback) {
		registerModelEvent(MODEL_EVENT_DELETING, callback);
	}
	
	protected static void deleted(ModelEventInterface callback) {
		registerModelEvent(MODEL_EVENT_DELETED, callback);
	}
	
	protected static void restoring(ModelEventInterface callback) {
		registerModelEvent(MODEL_EVENT_RESTORING, callback);
	}
	
	protected static void restored(ModelEventInterface callback) {
		registerModelEvent(MODEL_EVENT_RESTORED, callback);
	}
}
