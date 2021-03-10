package client.gui.views.loginview;

import client.gui.views.ViewController;
import client.gui.viewmodel.LoginViewModel;
import client.core.ViewHandler;
import client.gui.viewmodel.ViewModel;
import javafx.scene.control.TextField;

import java.io.IOException;


public class LoginViewController implements ViewController {

	public TextField gamerTagLabel;
	private LoginViewModel loginViewModel;
	private ViewHandler viewHandler;


	public void init(ViewHandler viewHandler, ViewModel model) {
		this.viewHandler = viewHandler;
		this.loginViewModel = (LoginViewModel) model;


		gamerTagLabel.textProperty().bindBidirectional(loginViewModel.nameProperty());

	}


	public void loginButton() {
		loginViewModel.login();

		try {
			viewHandler.openView("Lobby");
		} catch (IOException e) {
			e.printStackTrace();
		}


	}
}
