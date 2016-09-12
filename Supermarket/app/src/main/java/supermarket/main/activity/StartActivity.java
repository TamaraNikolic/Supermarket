package supermarket.main.activity;

import android.app.DownloadManager;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;

import supermarket.main.R;
import supermarket.main.data.Constant;
import supermarket.main.data.DataCategory;
import supermarket.main.data.DataContainer;
import supermarket.main.data.response.ResponseCategory;
import supermarket.main.data.response.ResponseCity;
import supermarket.main.data.response.ResponseToken;
import supermarket.main.networking.DataLoader;
import supermarket.main.networking.GsonRequest;

public class StartActivity extends AppCompatActivity {

    private final String REQUEST_TAG="Start_activity";

    private GsonRequest<ResponseToken> mRequestToken;
    private GsonRequest<ResponseCategory>mResponceCategory;
    private GsonRequest<ResponseCity>mRequestCity;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_page);

        mRequestToken=new GsonRequest<ResponseToken>(Constant.GRT_TOKEN_URL+"?username="+Constant.APPLICATION_USERNAME+"&password="+Constant.APPLICATION_PASSWORD,
                Request.Method.GET, ResponseToken.class, new Response.Listener<ResponseToken>() {
            @Override
            public void onResponse(ResponseToken response) {
                DataContainer.TOKEN=response.data.results.token;
                DataLoader.addRequest(getApplicationContext(),mResponceCategory,REQUEST_TAG);
                DataLoader.addRequest(getApplicationContext(),mRequestCity,REQUEST_TAG);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            Toast.makeText(getApplicationContext(),error.getLocalizedMessage(),Toast.LENGTH_SHORT).show();
            }
        });

        mResponceCategory=new GsonRequest<ResponseCategory>(Constant.CATEGORY_URL + "?token=" + DataContainer.TOKEN, Request.Method.GET, ResponseCategory.class,
                new Response.Listener<ResponseCategory>() {
                    @Override
                    public void onResponse(ResponseCategory response) {
                        DataContainer.categories=response.data.results;

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        mRequestCity=new GsonRequest<ResponseCity>(Constant.CITY_URL + "?token=" + DataContainer.TOKEN,
                Request.Method.GET, ResponseCity.class, new Response.Listener<ResponseCity>() {
            @Override
            public void onResponse(ResponseCity response) {
            DataContainer.cities=response.data.results.townships;
                Toast.makeText(getApplicationContext(),DataContainer.cities.toString(),Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        DataLoader.addRequest(getApplicationContext(),mRequestToken,REQUEST_TAG);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        DataLoader.canselRequest(getApplicationContext(),REQUEST_TAG);
    }
}
