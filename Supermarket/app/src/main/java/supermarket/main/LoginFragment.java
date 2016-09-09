package supermarket.main;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

/**
 * Created by cubesschool3 on 9/7/16.
 */
public class LoginFragment extends android.support.v4.app.Fragment {
    private ImageView mIvPicture;
    private ImageView mIvAdd;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);

        mIvAdd=(ImageView)view.findViewById(R.id.imageViewAdd);
        mIvPicture=(ImageView)view.findViewById(R.id.imageViewPicture);


        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
