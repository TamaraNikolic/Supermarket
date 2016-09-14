package supermarket.main.tool;

import android.graphics.Color;
import android.view.View;

import supermarket.main.R;

/**
 * Created by cubesschool3 on 9/14/16.
 */
public class MessageObject {

    public static final int MESSAGE_ERROR=0,MESSAGE_SUCCESS=1,MESSAGE_INFO=2;
    public int color=Color.BLUE;

    public int stringResource;
    public int time;
    public int type;
    public OnMessageClickListener listener;
    public View view;

    public interface OnMessageClickListener {
       void onClick();
    }
    public MessageObject(){
        time=2400;
        stringResource= R.string.error_message;
        type=MESSAGE_ERROR;
        listener= null;
        checkColour();
    }

    public MessageObject(int stringResource,int time,int type, OnMessageClickListener listener,View view){

        this.listener=listener;
        this.type=type;
        this.time=time;
        this.stringResource=stringResource;
        this.view=view;
   checkColour();
    }
    private void checkColour(){
        if(type==MESSAGE_ERROR){
            this.color= Color.RED;
        }
        else if(type==MESSAGE_SUCCESS){
            this.color=Color.GREEN;
        }
        else {
            this.color=Color.YELLOW;
        }
    }
}
