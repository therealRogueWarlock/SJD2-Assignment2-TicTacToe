package util;

import transferobjects.Message;

public interface GameRoomModel extends Subject
{
    void placePiece();

    void sendMessage(Message message);
}
