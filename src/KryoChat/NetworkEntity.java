package KryoChat;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.Listener;

/**
 * Created by podko_000
 * At 1:38 on 12.01.14
 */

public class NetworkEntity extends Listener {
    protected static final int UDP_PORT = 54555;
    protected static final int TCP_PORT = 54556;
    private Boolean operationStarted = false;
    private Boolean operationFinished = false;

    protected void registrate(Kryo kryo)
    {
        kryo.register(AuthRequest.class);
        kryo.register(AuthResponse.class);
        kryo.register(ChatUser.class);
        kryo.register(Request.class);
        kryo.register(MessageRequest.class);
        kryo.register(MessageResponse.class);
    }

    protected void startOperation()
    {
        synchronized (this) {
            operationStarted = true;
        }
    }

    protected void finishOperation()
    {
        synchronized (this)
        {
            operationStarted = false;
            operationFinished = true;
        }
    }

    public void await()
    {
        if(!operationStarted)
            return;
        else {
            while (!operationFinished) {
                try {
                    Thread.currentThread().sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                }
            }

        }
    }
}
