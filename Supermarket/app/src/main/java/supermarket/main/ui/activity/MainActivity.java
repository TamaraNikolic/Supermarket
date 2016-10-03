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
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import supermarket.main.R;
import supermarket.main.adapter.ExtendableListAdapter;
import supermarket.main.adapter.MyAdapter;
import supermarket.main.adapter.OnItemClickListener;
import supermarket.main.components.CustomTextView;
import supermarket.main.data.Constant;
import supermarket.main.data.DataCategory;
import supermarket.main.data.DataContainer;
import supermarket.main.data.DataProduct;
import supermarket.main.data.response.ProductResponse;
import supermarket.main.data.response.ProductSearchResponse;
import supermarket.main.networking.DataLoader;
import supermarket.main.networking.GsonRequest;

public class MainActivity extends AppCompatActivity {

    private static final String REQUEST_TAG ="Main_activity" ;
    private RecyclerView mRecyclerView;
    private MyAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private DrawerLayout mDraverLayout;
    private ImageView mIvMenu;
    private ImageView mIvBasket;
    private RelativeLayout mPbLoading;


    private RelativeLayout mRelLayWarning;

    private ImageView mImageViewUSerDrawer;
    private CustomTextView mTextViewUserNameDrawer;
    private CustomTextView mTextViewEmailDrawer;

    private GsonRequest<ProductSearchResponse>mResponseProduct2;
    private GsonRequest<ProductSearchResponse>mResponseProduct3;
    public static ArrayList<DataProduct>mProductList=new ArrayList<>();
    public static ArrayList<DataCategory>mCategoryList=new ArrayList<>();

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

        mRelLayWarning=(RelativeLayout)findViewById(R.id.relativeLayoutWarning);
        mPbLoading=(RelativeLayout)findViewById(R.id.progressBarLoading);

        mProductList.addAll(DataContainer.products);
        if(mProductList.size()==0){
            mRelLayWarning.setVisibility(View.VISIBLE);
        }
        else{
            mRelLayWarning.setVisibility(View.GONE);
        }


        mLayoutManager = new GridLayoutManager(getApplicationContext(),2);
        mAdapter = new MyAdapter(getApplicationContext(), mProductList, new OnItemClickListener() {
            @Override
            public void onItemClick(DataProduct item) {
            }
        });

        expListView = (ExpandableListView) findViewById(R.id.extendibleListView);

        // preparing list data
        DataCategory home=new DataCategory();
        home.name="Home";
        mCategoryList.add(home);
        home.subcategories=new ArrayList<>();

        mCategoryList.addAll(DataContainer.categories);

        DataCategory settings=new DataCategory();
        settings.name="Settings";
        settings.subcategories=new ArrayList<>();
        mCategoryList.add(settings);
        DataCategory profil=new DataCategory();
        profil.name="Profile";
        profil.subcategories=new ArrayList<>();
        mCategoryList.add(profil);
        DataCategory signout=new DataCategory();
        signout.name="Sign Out";
        signout.subcategories=new ArrayList<>();
        mCategoryList.add(signout);

        listAdapter = new ExtendableListAdapter(this, mCategoryList);

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
                                        final int groupPosition, int childPosition, long id) {
                mDraverLayout.closeDrawer(GravityCompat.START);
                mProductList.clear();
                mAdapter.notifyDataSetChanged();
                mPbLoading.setVisibility(View.VISIBLE);
                mResponseProduct3 = new GsonRequest<ProductSearchResponse>(Constant.PRODUCT_URL + "?token="
                        + DataContainer.TOKEN + "&search=1&mlimit=100&category=" + mCategoryList.get(groupPosition).subcategories.get(childPosition).id,
                        Request.Method.GET, ProductSearchResponse.class,
                        new Response.Listener<ProductSearchResponse>() {
                            @Override
                            public void onResponse(ProductSearchResponse response) {
                                mProductList.addAll(response.data.results);
                                mPbLoading.setVisibility(View.GONE);
                                mAdapter.notifyDataSetChanged();
                                if (mProductList.size() == 0) {
                                    mRelLayWarning.setVisibility(View.VISIBLE);
                                } else {
                                    mRelLayWarning.setVisibility(View.GONE);
                                }
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), "greska", Toast.LENGTH_SHORT).show();
                    }
                });
                DataLoader.addRequest(getApplicationContext(), mResponseProduct3, REQUEST_TAG);

                return false;
            }
        });

        expListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView expandableListView, View view, int i, long l) {

                if (i == 0) {
                    mDraverLayout.closeDrawer(GravityCompat.START);
                } else if (i == mCategoryList.size() - 2) {
                    mDraverLayout.closeDrawer(GravityCompat.START);
                    startActivity(new Intent(getApplicationContext(), ProfileActivity.class));

                } else if (i == mCategoryList.size() - 3) {
                    mDraverLayout.closeDrawer(GravityCompat.START);
                    startActivity(new Intent(getApplicationContext(), SetttingsActivity.class));
                }  else if (i == mCategoryList.size() - 1) {
                    mDraverLayout.closeDrawer(GravityCompat.START);

                }
                else {
                    if (mCategoryList.get(i).subcategories.size() == 0) {
                        mDraverLayout.closeDrawer(GravityCompat.START);
                        mProductList.clear();
                        mAdapter.notifyDataSetChanged();
                        mPbLoading.setVisibility(View.VISIBLE);
                        mResponseProduct2 = new GsonRequest<ProductSearchResponse>(Constant.PRODUCT_URL + "?token="
                                + DataContainer.TOKEN + "&search=1&mlimit=100&category=" + mCategoryList.get(i).id, Request.Method.GET, ProductSearchResponse.class,
                                new Response.Listener<ProductSearchResponse>() {
                                    @Override
                                    public void onResponse(ProductSearchResponse response) {
                                        mProductList.addAll(response.data.results);
                                        mPbLoading.setVisibility(View.GONE);
                                        mAdapter.notifyDataSetChanged();
                                        if (mProductList.size() == 0) {
                                            mRelLayWarning.setVisibility(View.VISIBLE);
                                        } else {
                                            mRelLayWarning.setVisibility(View.GONE);
                                        }
                                    }
                                },
                                 new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(getApplicationContext(), "greska", Toast.LENGTH_SHORT).show();
                            }
                        });

                        DataLoader.addRequest(getApplicationContext(), mResponseProduct2, REQUEST_TAG);
                    }
                }


                return false;
            }
        });

    }


    }

