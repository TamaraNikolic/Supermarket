package supermarket.main.data.response;

import java.util.ArrayList;

import supermarket.main.data.DataCategory;

public class  ResponseCategory {

public ResponseCategoryPom data;

    public class ResponseCategoryPom extends BaseResponse {
       public ArrayList<DataCategory> results;
    }
}