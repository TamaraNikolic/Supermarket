package supermarket.main.ui.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import supermarket.main.R;

public class AddressConfirmActivity extends AppCompatActivity {

    private ImageView mIvBack;
    private Button mBtContinue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address_confirm);

        initComponents();
        addListeners();
    }
    private void addListeners() {
        mIvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        mBtContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    private void initComponents() {
        mBtContinue=(Button)findViewById(R.id.buttonContinue);
        mIvBack=(ImageView)findViewById(R.id.imageViewBack);
    }

}

