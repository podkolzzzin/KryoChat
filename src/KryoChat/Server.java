package KryoChat;

import com.esotericsoftware.kryonet.Connection;


import java.io.IOException;
import java.net.InetAddress;
import java.util.ArrayList;

/**
 * Created by podko_000
 * At 1:37 on 12.01.14
 */

public class Server extends NetworkEntity {

    private com.esotericsoftware.kryonet.Server server;
    private ArrayList<ChatUser> users = new ArrayList<ChatUser>();


    public Server()
    {
        server = new com.esotericsoftware.kryonet.Server();
        registrate(server.getKryo());


        server.start();
        try {
            server.bind(TCP_PORT, UDP_PORT);
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }




        server.addListener(this);
        Console.writeLine("server started, waiting for connections");
    }

    @Override public void received(Connection c, Object o)
    {
        if(o instanceof Request)
        {
            Request r = (Request)o;
            switch (r.getType())
            {
                case Request.AUTH:
                    Console.writeLine("connection request received");
                    server.sendToTCP(c.getID(), auth((AuthRequest) o, c));
                    Console.writeLine(((AuthRequest)r).getNickname()+" has joined  to chat room");
                    break;
                case Request.MESSAGE:
                    MessageRequest mr = (MessageRequest)r;
                    ChatUser user = getUserById(mr.getUserId());
                    mr.setNicknameFrom(user.getName());
                    if(mr.getNicknameTo()==null)
                        server.sendToAllTCP(mr);
                    else
                    {
                        ChatUser to = getUserByName(mr.getNicknameTo());
                        if(to!=null)
                            server.sendToTCP(to.getConnection().getID(), mr);
                        else
                            server.sendToTCP(user.getConnection().getID(), new MessageResponse("No such user in the room"));
                    }
            }
        }
    }

    private ChatUser getUserById(long userId) {
        for(ChatUser u: users) {
            if(u.getId()==userId)
                return u;
        }
        return null;
    }

    private boolean isUserNameValid(String nickname) {
        for(ChatUser u: users)
        {
            if(u.getName().equals(nickname)) return false;
        }

        return true;
    }

    public AuthResponse auth(AuthRequest o, Connection c) {
        if(isUserNameValid(o.getNickname()))
        {
            ChatUser u = new ChatUser(o.getNickname(), c);
            users.add(u);
            return new AuthResponse(u);
        }
        return new AuthResponse();
    }

    public ChatUser getUserByName(String name) {
        for(ChatUser u: users) {
            if(u.getName().equals(name))
                return u;
        }
        return null;
    }

}
