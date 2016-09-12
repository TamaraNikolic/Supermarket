package supermarket.main.activity;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import supermarket.main.R;

public class MainActivity extends AppCompatActivity {

    private ImageView mIvAdd;
    private ImageView mIvPicture;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mIvAdd=(ImageView)findViewById(R.id.imageViewAdd);
        mIvPicture=(ImageView)findViewById(R.id.imageViewPicture);

    }
}
