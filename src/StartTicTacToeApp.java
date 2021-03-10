import javafx.application.Application;
import server.networking.RunServer;

import java.io.IOException;

public class StartTicTacToeApp {

    public static void main(String[] args) throws IOException {

        Application.launch(TicTacToeGameClient.class);
    }
}
