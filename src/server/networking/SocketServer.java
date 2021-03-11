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
                SocketServerHandler socketServerHandler = new SocketServerHandler(socket, this);
                serverLobbyModel.addListener("gameRoomAdd", socketServerHandler);

                Thread socketThread = new Thread(socketServerHandler);
                socketThread.start();

            }

        }
    }


    public void loginPlayer(String playerName){
        serverLobbyModel.addPlayer(playerName);
    }

    public void createGameRoom(String playerName){
        serverLobbyModel.host(playerName);
    }


    public ServerLobbyModel getServerLobbyModel() {
        return serverLobbyModel;
    }


    public void run() {
        try {
            start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
