package client.gui.views.loginview;

import client.core.ViewHandler;
import client.gui.viewmodel.LoginViewModel;
import client.gui.viewmodel.ViewModel;
import client.gui.views.ViewController;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.io.IOException;

public class LoginViewController implements ViewController {

	@FXML private TextField gamerTagLabel;
	private LoginViewModel loginViewModel;
	private ViewHandler viewHandler;

	public void init(ViewHandler viewHandler, ViewModel model) {
		this.viewHandler = viewHandler;
		this.loginViewModel = (LoginViewModel) model;

		gamerTagLabel.textProperty().bindBidirectional(loginViewModel.nameProperty());

	}

	@Override
	public void swapScene(String scene) throws IOException {
		viewHandler.openView("Lobby");
	}

	public void loginButton() {
		loginViewModel.login();

		try {
			swapScene("lobby");
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
