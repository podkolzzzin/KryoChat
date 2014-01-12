package KryoChat;

/**
 * Created by podko_000
 * At 1:54 on 10.01.14
 */

public class AuthRequest extends Request{
    private String nickname;
    public AuthRequest(String nickname)
    {
        super();
        this.type = Request.AUTH;
        this.nickname = nickname;
    }

    public AuthRequest()
    {

    }

    public String getNickname() {
        return nickname;
    }
}
