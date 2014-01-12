package KryoChat;

/**
 * Created by podko_000
 * At 15:04 on 12.01.14
 */

public class MessageRequest extends Request {
    private String message, nicknameTo, nicknameFrom;
    private long userId;

    public MessageRequest()
    {
        super();
        this.type = MESSAGE;

    }

    public MessageRequest(String message, long from, String to)
    {
        super();
        this.type = MESSAGE;
        this.message = message;
        this.userId = from;
        this.nicknameTo = to;
    }

    public MessageRequest(String message, long  from)
    {
        super();
        this.type = MESSAGE;
        this.message = message;
        this.userId = from;
    }

    public String getMessage() {
        return message;
    }

    public String getNicknameTo() {
        return nicknameTo;
    }

    public String getNicknameFrom() {
        return nicknameFrom;
    }

    public long getUserId() {
        return userId;
    }

    public void setNicknameFrom(String nicknameFrom) {
        this.nicknameFrom = nicknameFrom;
    }
}
