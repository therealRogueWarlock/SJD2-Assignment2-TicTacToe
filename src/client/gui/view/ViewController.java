package client.gui.view;

import client.core.ViewHandler;
import client.gui.viewmodel.ViewModel;

public interface ViewController {

	public abstract void init(ViewHandler viewHandler, ViewModel model);

}
