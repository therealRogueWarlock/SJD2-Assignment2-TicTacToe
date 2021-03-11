package server.networking;

import server.model.ServerModelTester;

import java.io.IOException;

public class RunServer {


    public static void main(String[] args) throws IOException {
        SocketServer socketServer = new SocketServer();

        new Thread(socketServer::run).start();


        ServerModelTester serverModelTester = new ServerModelTester(socketServer.getServerLobbyModel());
        serverModelTester.start();

    }

}
