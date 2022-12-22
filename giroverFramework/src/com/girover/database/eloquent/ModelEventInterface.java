package com.girover.database.eloquent;

@FunctionalInterface
public interface ModelEventInterface {

	public void fire(Model model);
}
