
package supermarket.main.fragment;

import android.content.Context;
import android.content.Intent;
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
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import supermarket.main.R;
import supermarket.main.data.Constant;
import supermarket.main.data.DataContainer;
import supermarket.main.ui.activity.LoginActivity;
import supermarket.main.ui.activity.TermOfUseActivity;
import supermarket.main.data.User;

/**
 * Created by cubesschool3 on 9/7/16.
 */
public class RegistrationFragment extends Fragment{

    private static PostCommentResponseListener mPostCommentResponse;
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
    private TextView termOfUse;


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

        termOfUse=(TextView)view.findViewById(R.id.textViewCondition);

        termOfUse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), TermOfUseActivity.class));
            }
        });

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
                if(isValidEmail(mEtEmail.getText().toString())){
                    Toast.makeText(getActivity().getApplicationContext(),"Ne tacan email",Toast.LENGTH_SHORT).show();
                }
                else{

                String newsletter="no";
                if(mCbNewsletter.isChecked()){
                    newsletter="yes";
                }

                user=new User();

                  final  String pass=mEtconfirmPassword.getText().toString();
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
postNewUser(getActivity().getApplicationContext(),user, DataContainer.TOKEN);
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
    public static void postNewUser(final Context context, final User user, final String token){

        RequestQueue queue = Volley.newRequestQueue(context);
        StringRequest sr = new StringRequest(Request.Method.POST, Constant.SIGNUP_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Toast.makeText(context,"radi"+response,Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context,"ne radi"+error,Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<>();
                params.put("first_name","Tamara");
                params.put("last_name","Nikolic");
                params.put("email",user.email);
                params.put("password","tamara");
                params.put("password_retype","tamara");
                params.put("cell_phone","32423423");
                params.put("phone","435345353");
                params.put("fax","324234324");
                params.put("street","Starog kapetana ww");
                params.put("number","1");
                params.put("apartment","1");
                params.put("floor","1");
                params.put("entrency","1");
                params.put("city","Beograd");
                params.put("postal_code",""+11250);
                params.put("newsletter",""+0);
                params.put("day",""+15);
                params.put("year",""+1993);
                params.put("month",""+1);
                params.put("gender",""+1);
                params.put("user_type","buyer");
                params.put("company_name","");
                params.put("pib","");
                params.put("token",token);


                return params;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String,String> params = new HashMap<String, String>();
                params.put("Content-Type","application/x-www-form-urlencoded");
                return params;
            }
        };
        queue.add(sr);
    }

    public interface PostCommentResponseListener {
        public void requestStarted();
        public void requestCompleted();
        public void requestEndedWithError(VolleyError error);
    }

}
