package supermarket.main.ui.activity;

import android.app.Activity;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.squareup.otto.Subscribe;

import supermarket.main.R;
import supermarket.main.tool.BusProvider;
import supermarket.main.tool.MessageObject;

/**
 * Created by cubesschool3 on 9/14/16.
 */
public class ActivityMessage extends AppCompatActivity {


    private View mMessageView;
    private TextView mTvMessage;
    private LayoutInflater mInflater;
    private Object busEventListnere;
    private ViewGroup mRootView;
    private Animation myAnim;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mInflater=LayoutInflater.from(getApplicationContext());


        busEventListnere=new Object(){
            @Subscribe

            public void onMessageShow(MessageObject messageObject){
                mMessageView=mInflater.inflate(R.layout.layout_message, mRootView, false);
                 mRootView.addView(mMessageView);
              mTvMessage=(TextView)mMessageView.findViewById(R.id.textViewMessage);
                mTvMessage.setTextColor(messageObject.color);
                 myAnim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.animation_message);
                 myAnim.setDuration(messageObject.time);
                mMessageView.startAnimation(myAnim);
                Toast.makeText(getApplicationContext(),messageObject.stringResource,Toast.LENGTH_SHORT).show();
            }
        };
    }

    @Override
    public void onResume() {
        super.onResume();

        if(mRootView==null) {
            mRootView = (ViewGroup) findViewById(R.id.root);
        }

        BusProvider.getInstance().register(busEventListnere);
    }

    @Override
    public void onPause() {
        super.onPause();
        BusProvider.getInstance().unregister(busEventListnere);
    }



}
