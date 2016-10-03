package supermarket.main.ui.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.regex.Pattern;

import jp.wasabeef.glide.transformations.CropCircleTransformation;
import supermarket.main.R;
import supermarket.main.components.CustomEditText;
import supermarket.main.components.CustomTextView;
import supermarket.main.data.DataContainer;
import supermarket.main.data.User;

public class ProfileActivity extends AppCompatActivity {

    private CustomEditText mEtUsser;
    private CustomEditText  mEtSurname;
    private CustomEditText  mEtPassword;
    private CustomEditText  mEtconfirmPassword;
    private CustomEditText  mEtEmail;
    private CustomEditText  mEtPhone;
    private CustomEditText  mEtCellPhone;
    private CustomEditText  mEtFax;
    private CustomEditText  mEtStreet;
    private CustomEditText  mEtPib;
    private CustomEditText  mEtFirm;
    private CustomEditText  mEtGender;
    private CustomEditText  mEtBirth;
    private CustomEditText  mEtPostCode;
    private CustomEditText  mEtCity;
    private CustomEditText  mEtEntrency;
    private CustomEditText  mEtFlor;
    private CustomEditText  mEtAppartment;
    private CustomEditText  mEtNumber;
    private Switch switchLegacy;
    private Button buutonContiune;
    private CheckBox mCbNewsletter;
    private User user;
    private ImageView mIvFirm;
    private ImageView mIvPib;
    private CustomTextView termOfUse;

    private ImageView mIvAdd;
    private ImageView mIvPicture;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);


        initCoponents();
        addListeners();

        }

    private void addListeners() {
        termOfUse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), TermOfUseActivity.class));
            }
        });

        switchLegacy.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    mEtFirm.setVisibility(View.VISIBLE);
                    mEtPib.setVisibility(View.VISIBLE);
                    mIvFirm.setVisibility(View.VISIBLE);
                    mIvPib.setVisibility(View.VISIBLE);
                } else {
                    mEtFirm.setVisibility(View.GONE);
                    mEtPib.setVisibility(View.GONE);
                    mIvFirm.setVisibility(View.GONE);
                    mIvPib.setVisibility(View.GONE);
                }
            }
        });

        buutonContiune.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isValidEmail(mEtEmail.getText().toString())) {
                    Toast.makeText(getApplicationContext(), "Ne tacan email", Toast.LENGTH_SHORT).show();
                } else {

                    String newsletter = "no";
                    if (mCbNewsletter.isChecked()) {
                        newsletter = "yes";
                    }

                    user = new User();

                    final String pass = mEtconfirmPassword.getText().toString();
                    user.name = mEtUsser.getText().toString();
                    user.surname = mEtSurname.getText().toString();
                    user.password = mEtPassword.getText().toString();
                    user.email = mEtEmail.getText().toString();
                    user.phone = mEtPhone.getText().toString();
                    user.cellphone = mEtCellPhone.getText().toString();
                    user.fax = mEtFax.getText().toString();
                    user.street = mEtStreet.getText().toString();
                    user.number = mEtNumber.getText().toString();
                    user.flor = mEtFlor.getText().toString();
                    user.entrecy = mEtEntrency.getText().toString();
                    user.city = mEtCity.getText().toString();
                    user.appartment = mEtAppartment.getText().toString();
                    user.postalcode = mEtPostCode.getText().toString();
                    user.birth = mEtBirth.getText().toString();
                    user.gender = mEtGender.getText().toString();
                    user.firm = mEtPostCode.getText().toString();
                    user.pib = mEtBirth.getText().toString();
                    user.newsletter = newsletter;

                    LoginActivity.mViewPager.setCurrentItem(0);

                }
            }
        });

        mIvAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectImage();
            }
        });
    }

    private void initCoponents() {
        mEtUsser=(CustomEditText)findViewById(R.id.textViewUser);
        mEtSurname=(CustomEditText)findViewById(R.id.textViewSurname);
        mEtPassword=(CustomEditText)findViewById(R.id.textViewLozinka);
        mEtconfirmPassword=(CustomEditText)findViewById(R.id.textViewPonovljenaLozinka);
        mEtEmail=(CustomEditText)findViewById(R.id.textViewEmail);
        mEtPhone=(CustomEditText)findViewById(R.id.textViewPhone);
        mEtCellPhone=(CustomEditText)findViewById(R.id.textViewCellPhone);
        mEtFax=(CustomEditText)findViewById(R.id.textViewFax);
        mEtStreet=(CustomEditText)findViewById(R.id.textViewStreet);
        mEtNumber=(CustomEditText)findViewById(R.id.textViewNumber);
        mEtAppartment=(CustomEditText)findViewById(R.id.textViewAppartment);
        mEtFlor=(CustomEditText)findViewById(R.id.textViewFlor);
        mEtEntrency=(CustomEditText)findViewById(R.id.textViewEntrency);
        mEtCity=(CustomEditText)findViewById(R.id.textViewCity);
        mEtPostCode=(CustomEditText)findViewById(R.id.textViewPostalCode);
        mEtBirth=(CustomEditText)findViewById(R.id.textViewBirthday);
        mEtGender=(CustomEditText)findViewById(R.id.textViewGender);
        mEtFirm=(CustomEditText)findViewById(R.id.textViewFirm);
        mEtPib=(CustomEditText)findViewById(R.id.textViewPib);

        mIvFirm=(ImageView)findViewById(R.id.imageViewFirm);
        mIvPib=(ImageView)findViewById(R.id.imageViewPib);

        mCbNewsletter=(CheckBox)findViewById(R.id.checkBox);

        switchLegacy=(Switch)findViewById(R.id.switchLegacy);

        buutonContiune=(Button)findViewById(R.id.buttonContinue);

        termOfUse=(CustomTextView)findViewById(R.id.textViewCondition);

        mIvAdd = (ImageView) findViewById(R.id.imageViewAd);
        mIvPicture=(ImageView)findViewById(R.id.imageViewPicture);
    }

    private boolean isValidEmail(String email) {
        Pattern pattern = Patterns.EMAIL_ADDRESS;
        return pattern.matcher(email).matches();
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
                ProfileActivity.this);



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
