package server.networking;

import server.model.lobbymodel.ServerLobbyModel;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketServer {

    private ServerLobbyModel  serverLobbyModel;

    public SocketServer() {
        serverLobbyModel = new ServerLobbyModel();
    }

    public void start() throws IOException {

        try (ServerSocket serverSocket = new ServerSocket(1235)) {
            System.out.println("server.Server running");
            while (true) {

                Socket socket = serverSocket.accept();

                Thread socketThread = new Thread(new SocketServerHandler(socket, serverLobbyModel));
                socketThread.start();

            }

        }
    }


}
