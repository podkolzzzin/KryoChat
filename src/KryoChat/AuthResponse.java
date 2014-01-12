package KryoChat;

/**
 * Created by podko_000
 * At 2:42 on 10.01.14
 */

public class AuthResponse extends Request {
    private boolean success;
    private long userId;

    public AuthResponse(ChatUser u) {
        super();
        this.userId = u.getId();
        success = true;
        this.type = AUTH;
    }

    public AuthResponse()
    {
        success = false;
        this.userId = -1;
    }

    public boolean isSuccess() {
        return success;
    }

    public long getUserId() {
        return userId;
    }
}
