
package supermarket.main.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.Toast;

import java.util.regex.Pattern;

import supermarket.main.R;
import supermarket.main.activity.LoginActivity;
import supermarket.main.data.User;

/**
 * Created by cubesschool3 on 9/7/16.
 */
public class RegistrationFragment extends Fragment{

    private EditText mEtUsser;
    private EditText mEtSurname;
    private EditText mEtPassword;
    private EditText mEtconfirmPassword;
    private EditText mEtEmail;
    private EditText mEtPhone;
    private EditText mEtCellPhone;
    private EditText mEtFax;
    private EditText mEtStreet;
    private EditText mEtPib;
    private EditText mEtFirm;
    private EditText mEtGender;
    private EditText mEtBirth;
    private EditText mEtPostCode;
    private EditText mEtCity;
    private EditText mEtEntrency;
    private EditText mEtFlor;
    private EditText mEtAppartment;
    private EditText mEtNumber;
    private Switch switchLegacy;
    private Button buutonContiune;
    private CheckBox mCbNewsletter;
    private User user;
    private ImageView mIvFirm;
    private ImageView mIvPib;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_registration, container, false);
        mEtUsser=(EditText)view.findViewById(R.id.textViewUser);
        mEtSurname=(EditText)view.findViewById(R.id.textViewSurname);
        mEtPassword=(EditText)view.findViewById(R.id.textViewLozinka);
        mEtconfirmPassword=(EditText)view.findViewById(R.id.textViewPonovljenaLozinka);
        mEtEmail=(EditText)view.findViewById(R.id.textViewEmail);
        mEtPhone=(EditText)view.findViewById(R.id.textViewPhone);
        mEtCellPhone=(EditText)view.findViewById(R.id.textViewCellPhone);
        mEtFax=(EditText)view.findViewById(R.id.textViewFax);
        mEtStreet=(EditText)view.findViewById(R.id.textViewStreet);
        mEtNumber=(EditText)view.findViewById(R.id.textViewNumber);
        mEtAppartment=(EditText)view.findViewById(R.id.textViewAppartment);
        mEtFlor=(EditText)view.findViewById(R.id.textViewFlor);
        mEtEntrency=(EditText)view.findViewById(R.id.textViewEntrency);
        mEtCity=(EditText)view.findViewById(R.id.textViewCity);
        mEtPostCode=(EditText)view.findViewById(R.id.textViewPostalCode);
        mEtBirth=(EditText)view.findViewById(R.id.textViewBirthday);
        mEtGender=(EditText)view.findViewById(R.id.textViewGender);
        mEtFirm=(EditText)view.findViewById(R.id.textViewFirm);
        mEtPib=(EditText)view.findViewById(R.id.textViewPib);

        mIvFirm=(ImageView)view.findViewById(R.id.imageViewFirm);
        mIvPib=(ImageView)view.findViewById(R.id.imageViewPib);

        mCbNewsletter=(CheckBox)view.findViewById(R.id.checkBox);

        switchLegacy=(Switch)view.findViewById(R.id.switchLegacy);

        buutonContiune=(Button)view.findViewById(R.id.buttonContinue);

        switchLegacy.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    mEtFirm.setVisibility(View.VISIBLE);
                    mEtPib.setVisibility(View.VISIBLE);
                    mIvFirm.setVisibility(View.VISIBLE);
                    mIvPib.setVisibility(View.VISIBLE);
                }
                else{
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
                if(!isValidEmail(mEtEmail.getText().toString())){
                    Toast.makeText(getActivity().getApplicationContext(),"Ne tacan email",Toast.LENGTH_SHORT).show();
                }
                else{

                String newsletter="no";
                if(mCbNewsletter.isChecked()){
                    newsletter="yes";
                }

                user=new User();
                user.name=mEtUsser.getText().toString();
                user.surname=mEtSurname.getText().toString();
                user.password=mEtPassword.getText().toString();
                user.email=mEtEmail.getText().toString();
                user.phone=mEtPhone.getText().toString();
                user.cellphone=mEtCellPhone.getText().toString();
                user.fax=mEtFax.getText().toString();
                user.street=mEtStreet.getText().toString();
                user.number=mEtNumber.getText().toString();
                user.flor=mEtFlor.getText().toString();
                user.entrecy=mEtEntrency.getText().toString();
                user.city=mEtCity.getText().toString();
                user.appartment=mEtAppartment.getText().toString();
                user.postalcode=mEtPostCode.getText().toString();
                user.birth=mEtBirth.getText().toString();
                user.gender=mEtGender.getText().toString();
                user.firm=mEtPostCode.getText().toString();
                user.pib=mEtBirth.getText().toString();
                user.newsletter=newsletter;

                    LoginActivity.mViewPager.setCurrentItem(0);

                }
            }
        });

        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    private boolean isValidEmail(String email) {
        Pattern pattern = Patterns.EMAIL_ADDRESS;
        return pattern.matcher(email).matches();
    }
}
