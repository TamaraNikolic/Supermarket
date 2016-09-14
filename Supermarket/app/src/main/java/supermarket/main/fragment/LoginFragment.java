package supermarket.main.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import supermarket.main.R;
import supermarket.main.ui.activity.ForgotPassActivity;

/**
 * Created by cubesschool3 on 9/7/16.
 */
public class LoginFragment extends android.support.v4.app.Fragment {
    private ImageView mIvPicture;
    private ImageView mIvAdd;
    private EditText mEtUsser;
    private EditText mEtPassword;
    private TextView mForgotPass;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);



        mEtPassword=(EditText)view.findViewById(R.id.textView2);
        mEtUsser=(EditText)view.findViewById(R.id.textView);
        mForgotPass=(TextView)view.findViewById(R.id.textView3);

        mForgotPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), ForgotPassActivity.class));
            }
        });


        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
