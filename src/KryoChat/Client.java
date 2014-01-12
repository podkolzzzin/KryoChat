package KryoChat;

import com.esotericsoftware.kryonet.Connection;

import java.io.IOException;

/**
 * Created by podko_000
 * At 1:37 on 12.01.14
 */

public class Client extends NetworkEntity {

    private com.esotericsoftware.kryonet.Client client;
    private long self;

    public Client()
    {
        client = new com.esotericsoftware.kryonet.Client();
        registrate(client.getKryo());
        client.addListener(this);
    }

    public void connect(String ip, String nickname) throws IOException {
        startOperation();
        client.start();
        client.connect(5000, ip, NetworkEntity.TCP_PORT, NetworkEntity.UDP_PORT);
        client.sendTCP(login(nickname));
    }

    @Override public void received(Connection c, Object o)
    {
        if(o instanceof Request)
        {
            Request r = (Request) o;
            switch (r.getType())
            {
                case Request.AUTH:
                    if(((AuthResponse)r).isSuccess()) {
                        Console.writeLine("You are in the chat room");
                        self = ((AuthResponse)r).getUserId();
                    }
                    else
                        Console.writeLine("You nickname is invalid");
                    finishOperation();
                    break;
                case Request.MESSAGE:
                    MessageRequest mr = (MessageRequest) r;
                    if(mr.getNicknameTo()==null)
                        Console.writeLine("\r\nPublic message received");
                    else
                        Console.writeLine("\r\nPrivate message received");
                    Console.writeLine("From: "+mr.getNicknameFrom());
                    Console.writeLine("Text: "+mr.getMessage());
                    Console.write("?>");
                    break;
                case Request.MESSAGE_RESPONSE:
                    Console.writeLine("Impossible to deliver your message");
                    Console.writeLine("Reason:"+((MessageResponse)r).getReason());
                    Console.write("?>");
                    break;
            }
        }
    }

    public AuthRequest login(String nickname) {
        return new AuthRequest(nickname);
    }

    public void sendMessage(String endPoint, String message) {
        startOperation();
        MessageRequest mr = new MessageRequest(message, self, endPoint);
        client.sendTCP(mr);
        finishOperation();
    }

    public void sendMessage(String message) {
        startOperation();
        MessageRequest mr = new MessageRequest(message, self);
        client.sendTCP(mr);
        finishOperation();
    }
}
