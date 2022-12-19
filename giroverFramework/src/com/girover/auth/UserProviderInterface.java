package com.girover.auth;

import com.girover.database.eloquent.Model;

public interface UserProviderInterface {
	public Model retriveByCredentials(String...loginCredentials);
}
