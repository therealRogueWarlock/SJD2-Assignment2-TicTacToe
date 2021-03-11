package server.networking;

import server.model.lobbymodel.ServerLobbyModel;
import transferobjects.Message;

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
            System.out.println("Server running");
            while (true) {

                Socket socket = serverSocket.accept();
                SocketServerHandler socketServerHandler = new SocketServerHandler(socket, this);

                serverLobbyModel.addListener("gameRoomAdd", socketServerHandler);
                serverLobbyModel.addListener("messageAdded", socketServerHandler);

                Thread socketThread = new Thread(socketServerHandler);
                socketThread.start();

            }

        }
    }


    public void loginPlayer(String playerName){
        serverLobbyModel.addPlayer(playerName);
    }

    public void createGameRoom(SocketServerHandler socketServerHandler, String playerName){
        serverLobbyModel.host(socketServerHandler, playerName);
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

    public void joinGameRoom(SocketServerHandler socketServerHandler, int roomId) {
        System.out.println("SocketServer call join on server lobby model");
        serverLobbyModel.join(socketServerHandler, roomId);

    }

    public void addMessage(Message message){
        serverLobbyModel.addMessage(message);
    }


}
