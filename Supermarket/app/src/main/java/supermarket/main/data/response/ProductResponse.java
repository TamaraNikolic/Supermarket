package supermarket.main.data.response;

import android.provider.ContactsContract;

import java.util.ArrayList;

import supermarket.main.data.DataCategory;
import supermarket.main.data.DataProduct;

/**
 * Created by Alen on 16.9.2016..
 */
public class ProductResponse {

    public ResponseProductPom data;

    public class ResponseProductPom extends BaseResponse {
        public ArrayList<DataProduct> results;
    }
}