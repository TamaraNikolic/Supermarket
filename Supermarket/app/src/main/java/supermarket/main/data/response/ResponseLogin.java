package supermarket.main.data.response;

import java.util.ArrayList;

import supermarket.main.data.DataLogin;
import supermarket.main.data.DataToken;

/**
 * Created by Alen on 16.9.2016..
 */
public class ResponseLogin {

    public ResponseLoginPom data;

    public class ResponseLoginPom extends BaseResponse{

    public DataLogin results;
    public String token;
    public String login_token;
}
}
