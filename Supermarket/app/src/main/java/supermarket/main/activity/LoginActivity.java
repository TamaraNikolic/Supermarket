package supermarket.main.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import jp.wasabeef.glide.transformations.CropCircleTransformation;
import supermarket.main.adapter.PagerAdapter;
import supermarket.main.R;

public class LoginActivity extends AppCompatActivity {


    //get Token-uvek se koristi!!!, get CityList, get ReservationList, get Categry, get HomeProductList

    //da li je login ili ne ako jeste otvara se main activity a ako nije otvata se login accitivity

    //Glide biblioteka za slike

    private TabLayout mTabLayout;
    private PagerAdapter mPagerAdapter;
    public static ViewPager mViewPager;
    private ImageView mIvAdd;
    private ImageView mIvPicture;
    private ImageView mIvSupermarket;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initComponents();
        addListneres();


    }
    public void initComponents(){
        mTabLayout=(TabLayout)findViewById(R.id.tab_layout);
        mTabLayout.addTab(mTabLayout.newTab().setText(R.string.login));
        mTabLayout.addTab(mTabLayout.newTab().setText(R.string.registration));
        mTabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        mIvSupermarket=(ImageView)findViewById(R.id.imageViewSupermarket);

        mIvAdd = (ImageView) findViewById(R.id.imageViewAdd);
        mIvPicture=(ImageView)findViewById(R.id.imageViewPicture);
        mPagerAdapter=new PagerAdapter(getSupportFragmentManager(),mTabLayout.getTabCount());
        mViewPager = (ViewPager) findViewById(R.id.pager);

        mViewPager.setAdapter(mPagerAdapter);




    }
    public void addListneres(){
        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTabLayout));

mIvAdd.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        selectImage();
    }
});
        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mPagerAdapter.getItem(tab.getPosition()).onAttach(getApplicationContext());
                mViewPager.setCurrentItem(tab.getPosition());
                if (tab.getPosition() == 0) {

                    mIvAdd.setVisibility(View.GONE);
                    mIvPicture.setVisibility(View.GONE);
                    mIvSupermarket.setVisibility(View.VISIBLE);

                } else if (tab.getPosition() == 1) {
                    mIvAdd.setVisibility(View.VISIBLE);
                    mIvPicture.setVisibility(View.VISIBLE);
                    mIvSupermarket.setVisibility(View.GONE);

                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                mPagerAdapter.getItem(tab.getPosition()).onAttach(getApplicationContext());
            }
        });

        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
    private void selectImage() {

        final CharSequence[] items = { "Take Photo", "Choose from Galery",
                "Cancel" };

        TextView title = new TextView(getApplicationContext());
        title.setText("Add Photo!");
        title.setBackgroundColor(Color.BLACK);
        title.setPadding(10, 15, 15, 10);
        title.setGravity(Gravity.CENTER);
        title.setTextColor(Color.WHITE);
        title.setTextSize(22);

        ImageView image=new ImageView(getApplicationContext());

        AlertDialog.Builder builder = new AlertDialog.Builder(
                LoginActivity.this);



        builder.setCustomTitle(title);

        // builder.setTitle("Add Photo!");
        builder.setItems(items, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int item) {
                if (items[item].equals("Take Photo")) {
                    // Intent intent = new
                    // Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                    Intent takePicture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(takePicture, 0);
                /*
                 * File photo = new
                 * File(Environment.getExternalStorageDirectory(),
                 * "Pic.jpg"); intent.putExtra(MediaStore.EXTRA_OUTPUT,
                 * Uri.fromFile(photo)); imageUri = Uri.fromFile(photo);
                 */
                    // startActivityForResult(intent,TAKE_PICTURE);

                    Intent intents = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);


                } else if (items[item].equals("Choose from Galery")) {
                    Intent pickPhoto = new Intent(Intent.ACTION_PICK,
                            android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(pickPhoto, 1);

                } else if (items[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }
    protected void onActivityResult(int requestCode, int resultCode, Intent imageReturnedIntent) {
        super.onActivityResult(requestCode, resultCode, imageReturnedIntent);
        switch(requestCode) {
            case 0:
                if(resultCode == RESULT_OK){
                    Uri selectedImage = imageReturnedIntent.getData();
                    Glide
                            .with(getApplicationContext())
                            .load(selectedImage).bitmapTransform(new CropCircleTransformation(getApplicationContext()))
                            .into(mIvPicture);


                }

                break;
            case 1:
                if(resultCode == RESULT_OK){
                    Uri selectedImage = imageReturnedIntent.getData();
                    Glide
                            .with(getApplicationContext())
                            .load(selectedImage).bitmapTransform(new CropCircleTransformation(getApplicationContext()))
                            .into(mIvPicture);;
                }
                break;
        }
    }
}
