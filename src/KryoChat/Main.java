package KryoChat;

import KryoChat.Chat;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;
import sun.io.CharToByteASCII;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
	    new Chat();
    }
}
