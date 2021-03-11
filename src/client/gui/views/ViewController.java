package client.gui.views;

import client.core.ViewHandler;
import client.gui.viewmodel.ViewModel;

import java.io.IOException;

public interface ViewController {

	void init(ViewHandler viewHandler, ViewModel model);
	void swapScene(String scene) throws IOException;

}
