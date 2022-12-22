package com.girover.view;

import java.io.FileNotFoundException;
import java.net.URL;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class View extends Stage{

	private static String viewBasePath;
	private Stage stage;
	private Scene scene;

	public View(String fxml) {
		this(new Stage(), fxml);
	}
	
	public View(Stage stage, String fxml) {
		this.stage = stage;
		scene = scene(fxml);
		this.setScene(scene);
	}

	public static void setViewsPath(String fxmlFolderPath) {
		viewBasePath = fxmlFolderPath;
	}
	
	private URL getSceneUrl(String sceneName) {
		
		URL fileUrl = getClass().getResource(viewBasePath + "/" + sceneName + ".fxml");
		
		return fileUrl;
	}

	public Scene scene(String sceneName) {
		try {

			URL fileUrl = getSceneUrl(sceneName);
			
			if (fileUrl == null) {
				throw new FileNotFoundException();
			}

			Pane parent = FXMLLoader.load(fileUrl);
			
			return new Scene(parent);
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public void switchScene(String scenePath) {
		try {
			this.scene = scene(scenePath);
			this.stage.setScene(this.scene);
		} catch (Exception e) {
			System.out.println("path error: " + scenePath);
		}
	}
	
	public View title(String title) {
		this.setTitle(title);
		
		return this;
	}
	
	public View maximized() {
		this.setMaximized(true);
		
		return this;
	}
}
