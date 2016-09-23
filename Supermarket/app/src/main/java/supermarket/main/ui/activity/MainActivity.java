package supermarket.main.ui.activity;

import android.content.Intent;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import supermarket.main.R;
import supermarket.main.adapter.MyAdapter;
import supermarket.main.adapter.OnItemClickListener;
import supermarket.main.data.DataContainer;
import supermarket.main.data.DataProduct;
import supermarket.main.data.response.ProductResponse;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private MyAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private DrawerLayout mDraverLayout;
    private ImageView mIvMenu;
    private ImageView mIvBasket;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initComponents();
        addListners();

    }

    public void initComponents(){
        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        mDraverLayout=(DrawerLayout)findViewById(R.id.my_drawer_layout);
        mIvMenu=(ImageView)findViewById(R.id.imageViewHam);
        mIvBasket=(ImageView)findViewById(R.id.imageViewBasket);

        mLayoutManager = new GridLayoutManager(getApplicationContext(),2);
        mAdapter = new MyAdapter(getApplicationContext(), DataContainer.products, new OnItemClickListener() {
            @Override
            public void onItemClick(DataProduct item) {
            }
        });


        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
    }

    public void addListners(){
        mIvMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDraverLayout.openDrawer(Gravity.LEFT);
            }
        });

        mIvBasket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),BasketAcitivity.class));
            }
        });
    }
    }

