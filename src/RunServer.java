import server.model.ServerModelTester;
import server.networking.SocketServer;

import java.io.IOException;

public class RunServer {


    public static void main(String[] args) {
        SocketServer socketServer = new SocketServer();

        new Thread(socketServer::run).start();


        ServerModelTester serverModelTester = new ServerModelTester(socketServer.getServerLobbyModel());
        serverModelTester.start();

    }

}
