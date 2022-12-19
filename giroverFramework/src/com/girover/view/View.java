package com.girover.view;

import java.io.FileNotFoundException;
import java.net.URL;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class View {

	private static String viewPath;
	private String fxml;
	private Stage stage;
	
	public View(String fxml) {
		this.fxml = fxml;
	}
	
	public static void setFxmlPath(String fxmlFolderPath) {
		viewPath = fxmlFolderPath;
	}
	
	public Pane getPage(String pageName) {
		try {
			
			URL fileUrl = getClass().getResource(viewPath + "/" + pageName + ".fxml");
			if (fileUrl == null) {
				throw new FileNotFoundException();
			}
			
			Pane view = FXMLLoader.load(fileUrl);
			return view;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}
}
