package supermarket.main.data.response;

import supermarket.main.data.DataToken;

/**
 * Created by cubesschool3 on 9/12/16.
 */
public class ResponseToken {

    public ResponseTokenPom data;

    public class ResponseTokenPom extends BaseResponse{

        public DataToken results;
    }
}
