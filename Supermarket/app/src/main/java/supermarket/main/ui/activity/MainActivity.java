package supermarket.main.ui.activity;

import android.content.Intent;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import supermarket.main.R;
import supermarket.main.adapter.ExtendableListAdapter;
import supermarket.main.adapter.MyAdapter;
import supermarket.main.adapter.OnItemClickListener;
import supermarket.main.components.CustomTextView;
import supermarket.main.data.DataCategory;
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

    private ImageView mImageViewUSerDrawer;
    private CustomTextView mTextViewUserNameDrawer;
    private CustomTextView mTextViewEmailDrawer;

    ExtendableListAdapter listAdapter;
    ExpandableListView expListView;



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
        mImageViewUSerDrawer=(ImageView)findViewById(R.id.imageViewUserPicture);
        mTextViewEmailDrawer=(CustomTextView)findViewById(R.id.userEmail);
        mTextViewEmailDrawer.setText("tamaranikolic13@yahoo.com");
        mTextViewUserNameDrawer=(CustomTextView)findViewById(R.id.textViewUserName);
        mTextViewUserNameDrawer.setText("Tamara Nikolic");

        mLayoutManager = new GridLayoutManager(getApplicationContext(),2);
        mAdapter = new MyAdapter(getApplicationContext(), DataContainer.products, new OnItemClickListener() {
            @Override
            public void onItemClick(DataProduct item) {
            }
        });

        expListView = (ExpandableListView) findViewById(R.id.extendibleListView);

        // preparing list data

        listAdapter = new ExtendableListAdapter(this, DataContainer.categories);

        // setting list adapter
        expListView.setAdapter(listAdapter);
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
                startActivity(new Intent(getApplicationContext(), BasketAcitivity.class));
            }
        });

        expListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            // Listview on child click listener
            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {
                Toast.makeText(
                        getApplicationContext(),
                        DataContainer.categories.get(groupPosition)
                                + " : "
                                + DataContainer.categories.get(groupPosition).subcategories.get(childPosition), Toast.LENGTH_SHORT)
                        .show();
                return false;
            }
        });

        expListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView expandableListView, View view, int i, long l) {

                if(i==0){
                    mDraverLayout.closeDrawer(GravityCompat.START);
                }
                if(i==DataContainer.categories.size()-2){

                 startActivity(new Intent(getApplicationContext(), ProfileActivity.class));
                }
                if(i==DataContainer.categories.size()-3){

                    startActivity(new Intent(getApplicationContext(), SetttingsActivity.class));
                }
                return false;
            }
        });
    }


    }

