package client.gui.views.loginview;

import client.core.ViewHandler;
import client.gui.viewmodel.LoginViewModel;
import client.gui.viewmodel.ViewModel;
import client.gui.views.ViewController;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;

public class LoginViewController implements ViewController {

	public Label error;
	@FXML private TextField gamerTagLabel;
	private LoginViewModel loginViewModel;
	private ViewHandler viewHandler;

	@Override
	public void init(ViewHandler viewHandler, ViewModel model) {
		this.viewHandler = viewHandler;
		this.loginViewModel = (LoginViewModel) model;

		gamerTagLabel.textProperty().bindBidirectional(loginViewModel.nameProperty());

	}

	public void loginButton() {

		if (loginViewModel.tryLogin()) {
			try {
				swapScene("Lobby");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	@Override
	public void swapScene(String scene) throws IOException {
		viewHandler.openView(scene);
	}

}
