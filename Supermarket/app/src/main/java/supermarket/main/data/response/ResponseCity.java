package supermarket.main.data.response;

import java.util.ArrayList;

import supermarket.main.data.DataCity;

/**
 * Created by cubesschool3 on 9/12/16.
 */
public class ResponseCity  {


    public ResponseCityPom2 data;

    public class ResponseCityPom1{

        public ArrayList<DataCity>townships;

    }

    public class ResponseCityPom2 extends  BaseResponse{
        public ResponseCityPom1 results;
    }
}
