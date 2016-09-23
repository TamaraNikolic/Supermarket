package supermarket.main.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;

import supermarket.main.R;
import supermarket.main.data.Constant;
import supermarket.main.data.DataContainer;
import supermarket.main.data.response.ResponseCategory;
import supermarket.main.data.response.ResponseLogin;
import supermarket.main.networking.DataLoader;
import supermarket.main.networking.GsonRequest;
import supermarket.main.ui.activity.ForgotPassActivity;
import supermarket.main.ui.activity.MainActivity;
import supermarket.main.ui.activity.StartActivity;

/**
 * Created by cubesschool3 on 9/7/16.
 */
public class LoginFragment extends android.support.v4.app.Fragment {

    private EditText mEtUsser;
    private EditText mEtPassword;
    private TextView mForgotPass;
    private Button mButtonLogin;


    private GsonRequest<ResponseLogin> mResponceLogin;
    private final String REQUEST_TAG1="Login_activity";


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);



        mEtPassword=(EditText)view.findViewById(R.id.textView2);
        mEtUsser=(EditText)view.findViewById(R.id.textView);
        mForgotPass=(TextView)view.findViewById(R.id.textView3);
        mButtonLogin=(Button)view.findViewById(R.id.buttonContinue);

        mButtonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                mResponceLogin=new GsonRequest<ResponseLogin>("http://shop.cubes.rs/phone-user?email=tamaranikolic13@yahoo.com&password=tamara&token="+ DataContainer.TOKEN, Request.Method.GET, ResponseLogin.class,
                        new Response.Listener<ResponseLogin>() {
                            @Override
                            public void onResponse(ResponseLogin response) {
                                DataContainer.login_token=response.data.login_token;
                                Toast.makeText(getActivity().getApplicationContext(),""+response.data.token,Toast.LENGTH_SHORT).show();

                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getActivity().getApplicationContext(),error.getMessage(),Toast.LENGTH_SHORT).show();
                    }
                });

                DataLoader.addRequest(getActivity().getApplicationContext(), mResponceLogin, REQUEST_TAG1);
            }
        });

        mForgotPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), ForgotPassActivity.class));


            }
        });
if(true) {

    mResponceLogin=new GsonRequest<ResponseLogin>("http://shop.cubes.rs/phone-user?email=tamaranikolic13@yahoo.com&password=tamara&token="+ DataContainer.TOKEN, Request.Method.GET, ResponseLogin.class,
            new Response.Listener<ResponseLogin>() {
                @Override
                public void onResponse(ResponseLogin response) {
                    DataContainer.login_token=response.data.login_token;
                    Toast.makeText(getActivity().getApplicationContext(),""+response.data.token,Toast.LENGTH_SHORT).show();

                }
            }, new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {
            Toast.makeText(getActivity().getApplicationContext(),error.getMessage(),Toast.LENGTH_SHORT).show();
        }
    });

    DataLoader.addRequest(getActivity().getApplicationContext(), mResponceLogin, REQUEST_TAG1);

    startActivity(new Intent(getContext(), MainActivity.class));
    getActivity().finish();
}


        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
