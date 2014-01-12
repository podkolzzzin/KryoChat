package KryoChat;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;


import javax.smartcardio.CommandAPDU;
import java.io.IOException;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.util.ArrayList;

/**
 * Created by podko_000
 * At 0:54 on 09.01.14
 */

public class Chat extends Listener {
    private String nickname;
    private KryoChat.Client client;

    public Chat()
    {
        help();
        start();
    }

    public void help()
    {
        Console.writeLine();
        Console.writeLine("===================");
        Console.writeLine("KryoChat0.1 started");
        Console.writeLine("========help=======");
        Console.writeLine("server - starts server");
        Console.writeLine("connect {ip} - connects to chat on ip");
        Console.writeLine("help - shows program help");
        Console.writeLine("exit - terminate program");
        Console.writeLine("nickname {your_nickname} - set nickname");
        Console.writeLine("uc {nickname} {message} - send message to user");
        Console.writeLine("mc message - send message to everybody in the room");
        Console.writeLine("===================");
    }

    private void start()
    {
        while (true) {
            Console.write("?>");
            Command.parseCommand(Console.readLine()).execute(this);
        }
    }

    public void exit()
    {
        System.exit(-1);
    }

    public void connect(String ip) throws IOException {
        if(nickname==null)
        {
            Console.write("No nickname set, please set nickname ?>");
            nickname = Console.readLine();
            connect(ip);
            return;
        }

        client =new KryoChat.Client();
        client.connect(ip, nickname);

        Console.writeLine("connection request sent");
        client.await();
    }

    public void nickname(String name)
    {
        nickname = name;
    }

    public void server()
    {
        Server server = new Server();
    }

    public void uc(String endPoint, String message) //unicast
    {
        /*String endPoint = args[0];
        String message = args[1];*/
        client.sendMessage(endPoint, message);
        client.await();
    }

    public void mc(String message) //multicast
    {
        client.sendMessage(message);
        client.await();
    }
}
