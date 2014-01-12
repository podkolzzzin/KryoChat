package KryoChat;

import com.esotericsoftware.kryonet.Connection;

import java.net.InetAddress;

/**
 * Created by podko_000
 * At 2:06 on 10.01.14
 */

public class ChatUser {
    private String name;
    private Connection connection;
    private long id;

    public ChatUser(String name, Connection connection)
    {
        this.name = name;
        this.connection = connection;
        this.id = System.currentTimeMillis();
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Connection getConnection() {
        return connection;
    }
}
