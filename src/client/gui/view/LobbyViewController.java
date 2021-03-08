package client.gui.view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;

public class LobbyViewController
{

    @FXML public TableColumn listRoomNumber;
    @FXML public TableColumn listPlayerAmount;
    @FXML public TableColumn listPlayerNames;
    @FXML public ListView lobbyChat;
    @FXML public TextField textToSendLobby;

    public void joinGame(ActionEvent actionEvent)
    {
    }

    public void hostGame(ActionEvent actionEvent)
    {
    }

    public void quitGame(ActionEvent actionEvent)
    {
    }

    public void sendTextLobby(ActionEvent actionEvent)
    {
    }
}
