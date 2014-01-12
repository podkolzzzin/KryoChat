package KryoChat;

/**
 * Created by podko_000
 * At 15:36 on 12.01.14
 */

public class MessageResponse extends Request {
    private String reason;
    public MessageResponse()
    {
        super();
        type = MESSAGE_RESPONSE;
    }

    public MessageResponse(String reason) {
        super();
        type = MESSAGE_RESPONSE;
        this.reason = reason;
    }

    public String getReason() {
        return reason;
    }
}
