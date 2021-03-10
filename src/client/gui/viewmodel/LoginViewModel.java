package client.gui.viewmodel;

import client.model.loginmodel.ClientLoginModel;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import util.LoginModel;

import java.beans.PropertyChangeEvent;

public class LoginViewModel implements ViewModel {

	private StringProperty name;

	private ClientLoginModel clientLoginModel;

	public LoginViewModel(LoginModel loginModel) {
		clientLoginModel = (ClientLoginModel) loginModel;

		name = new SimpleStringProperty();

	}

	public void login() {
		clientLoginModel.login(name.getValue());
	}


	public StringProperty nameProperty() {
		return name;
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {

	}
}
