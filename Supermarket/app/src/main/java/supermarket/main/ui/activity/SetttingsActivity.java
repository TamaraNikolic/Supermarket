package supermarket.main.ui.activity;

import android.content.Intent;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;

import java.util.ArrayList;

import supermarket.main.R;
import supermarket.main.adapter.ExtendableListAdapter;
import supermarket.main.adapter.MyAdapter;
import supermarket.main.adapter.OnItemClickListener;
import supermarket.main.components.CustomTextView;
import supermarket.main.data.Constant;
import supermarket.main.data.DataCategory;
import supermarket.main.data.DataContainer;
import supermarket.main.data.DataProduct;
import supermarket.main.data.response.ProductSearchResponse;
import supermarket.main.networking.DataLoader;
import supermarket.main.networking.GsonRequest;

public class SetttingsActivity extends AppCompatActivity {

    private Switch mSwichNotifications;
    private ImageView mIvTermOfUse, mIvProfile,mIvSignOut,mIvSupport;

    private ImageView mImageViewUSerDrawer;
    private CustomTextView mTextViewUserNameDrawer;
    private CustomTextView mTextViewEmailDrawer;

    private RecyclerView mRecyclerView;
    private MyAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private DrawerLayout mDraverLayout;

    ExtendableListAdapter listAdapter;
    ExpandableListView expListView;
    private ImageView mIvMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setttings);

        initComponents();
        addListeners();
    }

    private void addListeners() {
        mIvProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),ProfileActivity.class));
                finish();
            }
        });
        mIvTermOfUse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), TermOfUseActivity.class));
                finish();
            }
        });
        mIvSupport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),SetttingsActivity.class));
                finish();
            }
        });
        mIvSignOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
            }
        });

        mIvMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDraverLayout.openDrawer(Gravity.LEFT);
            }
        });

        expListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            // Listview on child click listener
            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        final int groupPosition, int childPosition, long id) {


                return false;
            }
        });

        expListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView expandableListView, View view, int i, long l) {

                if (i == 0) {
                    mDraverLayout.closeDrawer(GravityCompat.START);
                    finish();
                } else if (i == MainActivity.mCategoryList.size() - 2) {
                    mDraverLayout.closeDrawer(GravityCompat.START);
                    startActivity(new Intent(getApplicationContext(), ProfileActivity.class));
                    finish();

                } else if (i == MainActivity.mCategoryList.size() - 3) {
                    mDraverLayout.closeDrawer(GravityCompat.START);
                    startActivity(new Intent(getApplicationContext(), SetttingsActivity.class));
                    finish();
                } else if (i == MainActivity.mCategoryList.size() - 1) {
                    mDraverLayout.closeDrawer(GravityCompat.START);
                    finish();
                } else {



                }

                return false;
            }
        });}


    private void initComponents() {
        mSwichNotifications=(Switch)findViewById(R.id.switcher);
        mIvProfile=(ImageView)findViewById(R.id.imageViewProfile);
        mIvSignOut=(ImageView)findViewById(R.id.imageViewSignOut);
        mIvTermOfUse=(ImageView)findViewById(R.id.imageViewTermOFUSe);
        mIvSupport=(ImageView)findViewById(R.id.imageViewSupport);

        mImageViewUSerDrawer=(ImageView)findViewById(R.id.imageViewUserPicture);
        mTextViewEmailDrawer=(CustomTextView)findViewById(R.id.userEmail);
        mTextViewEmailDrawer.setText("tamaranikolic13@yahoo.com");
        mTextViewUserNameDrawer=(CustomTextView)findViewById(R.id.textViewUserName);
        mTextViewUserNameDrawer.setText("Tamara Nikolic");
        mIvMenu=(ImageView)findViewById(R.id.imageViewHam);

        mDraverLayout=(DrawerLayout)findViewById(R.id.my_drawer_layout);
        mLayoutManager = new GridLayoutManager(getApplicationContext(),2);


        expListView = (ExpandableListView) findViewById(R.id.extendibleListView);

        // preparing list data


        listAdapter = new ExtendableListAdapter(this, MainActivity.mCategoryList);

        // setting list adapter
        expListView.setAdapter(listAdapter);





    }

}
