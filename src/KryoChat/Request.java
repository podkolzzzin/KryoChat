package KryoChat;

import com.esotericsoftware.kryo.KryoSerializable;

/**
 * Created by podko_000
 * At 1:36 on 10.01.14
 */

public class Request {
    public static final int AUTH = 1;
    public static final int MESSAGE = 2;
    public static final int MESSAGE_RESPONSE = 3;
    private long created; // timestamp
    protected int type;


    public Request()
    {
        created = System.currentTimeMillis();
    }

    public int getType() {
        return type;
    }
}
