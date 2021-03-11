package client.core;

import client.gui.viewmodel.ViewModel;
import client.gui.views.ViewController;
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

		loader.setLocation(getClass().getResource("../gui/views/" + viewToOpen.toLowerCase() + "view/" + viewToOpen + "View.fxml"));
		root = loader.load();
		ViewController viewController = loader.getController();
		viewController.init(this, getViewModelByViewName(viewToOpen));

		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

	private ViewModel getViewModelByViewName(String viewName) {
		// Lambda Expression for et Switch på ViewName
		return switch (viewName) {
			case "Login" -> viewModelFactory.getLoginViewModel();
			case "Lobby" -> viewModelFactory.getLobbyViewModel();
			case "GameRoom" -> viewModelFactory.getGameRoomViewModel();
			default -> null;
		};
// 		Skrevet ud til et normalt switch
//		switch (viewName) {
//			case "Login":
//				return viewModelFactory.getLoginViewModel();
//			case "Lobby":
//				return viewModelFactory.getLobbyViewModel();
//			case "GameRoom":
//				return viewModelFactory.getGameRoomViewModel();
//			default:
//				return null;
//		}
	}

}
