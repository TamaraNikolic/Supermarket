package supermarket.main.data.response;

import java.util.ArrayList;

import supermarket.main.data.DataProduct;

/**
 * Created by cubesschool3 on 9/30/16.
 */
public class ProductSearchResponse {
    public ResponseProductSearchPom data;

    public class ResponseProductSearchPom extends BaseResponse {
        public ArrayList<DataProduct> results;
    }
}