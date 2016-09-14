package supermarket.main.ui.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.squareup.otto.Subscribe;

import supermarket.main.R;
import supermarket.main.data.Constant;
import supermarket.main.data.DataContainer;
import supermarket.main.data.response.ResponseCategory;
import supermarket.main.data.response.ResponseCity;
import supermarket.main.data.response.ResponseToken;
import supermarket.main.networking.DataLoader;
import supermarket.main.networking.GsonRequest;
import supermarket.main.tool.BusProvider;
import supermarket.main.tool.MessageObject;

public class StartActivity extends ActivityMessage {

    private final String REQUEST_TAG="Start_activity";

    private GsonRequest<ResponseToken> mRequestToken;
    private GsonRequest<ResponseCategory>mResponceCategory;
    private GsonRequest<ResponseCity>mRequestCity;
    private int count=0;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_start_page);



        mRequestToken=new GsonRequest<ResponseToken>(Constant.GRT_TOKEN_URL+"?username="+Constant.APPLICATION_USERNAME+"&password="+Constant.APPLICATION_PASSWORD,
                Request.Method.GET, ResponseToken.class, new Response.Listener<ResponseToken>() {
            @Override
            public void onResponse(ResponseToken response) {
                DataContainer.TOKEN=response.data.results.token;
                count++;
                startLogin(count);
                DataLoader.addRequest(getApplicationContext(),mResponceCategory,REQUEST_TAG);
                DataLoader.addRequest(getApplicationContext(),mRequestCity,REQUEST_TAG);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                BusProvider.getInstance().post(new MessageObject());
            }
        });

        mResponceCategory=new GsonRequest<ResponseCategory>(Constant.CATEGORY_URL + "?token=" + DataContainer.TOKEN, Request.Method.GET, ResponseCategory.class,
                new Response.Listener<ResponseCategory>() {
                    @Override
                    public void onResponse(ResponseCategory response) {
                        DataContainer.categories=response.data.results;
                        count++;
                        startLogin(count);
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
                count++;
                startLogin(count);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        DataLoader.addRequest(getApplicationContext(),mRequestToken,REQUEST_TAG);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        DataLoader.canselRequest(getApplicationContext(), REQUEST_TAG);
    }

    private synchronized void startLogin(int num){
        if(num==3){
            startActivity(new Intent(getApplicationContext(),LoginActivity.class));
            finish();
        }
    }


}
