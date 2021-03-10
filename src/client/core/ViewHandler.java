package client.core;

import client.gui.views.ViewController;
import client.gui.viewmodel.ViewModel;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ViewHandler {

	private ViewModelFactory viewModelFactory;
	private Stage stage;

	public ViewHandler(Stage stage, ViewModelFactory viewModelFactory) {
		this.stage = stage;
		this.viewModelFactory = viewModelFactory;
	}

	public void start() throws IOException {
		openView("Login");
	}

	public void openView(String viewToOpen) throws IOException {
		Scene scene;
		Parent root;

		FXMLLoader loader = new FXMLLoader();

		loader.setLocation(getClass().getResource("../gui/views/" + viewToOpen.toLowerCase() +"view/"+ viewToOpen + "View.fxml"));
		root = loader.load();
		ViewController viewController = loader.getController();
		viewController.init(this, getViewModelByViewName(viewToOpen));

		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}


	private ViewModel getViewModelByViewName(String viewName){

		if (viewName.equals("Login")){
			return viewModelFactory.getLoginViewModel();
		}

		return null;
	}






}
