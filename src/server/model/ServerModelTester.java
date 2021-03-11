package server.model;

import server.model.lobbymodel.ServerLobbyModel;

public class ServerModelTester {

    ServerLobbyModel serverLobbyModel;

    public ServerModelTester(ServerLobbyModel serverLobbyModel){
        this.serverLobbyModel = serverLobbyModel;

    }


    public void start(){
        while(true){

            System.out.println(serverLobbyModel.getPlayers());
            System.out.println(serverLobbyModel.getGameRooms());

            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }



    }



}
