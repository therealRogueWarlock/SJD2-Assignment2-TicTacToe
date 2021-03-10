package client.gui.viewmodel;

import server.model.Login;
import server.model.loginmodel.ServerLoginModel;
import server.model.loginmodel.LogInterface;
import Client.client.model.client.model.loginmodel.ClientLoginModel;

public class LoginViewModel implements ViewModel {

	private SimpleStringProperty name;

	private Login login;

	private ServerLoginModel serverLoginModel;

	private LogInterface logInterface;

	private ClientLoginModel clientLoginModel;

	public void login() {

	}

}
