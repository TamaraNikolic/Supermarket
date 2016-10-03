package supermarket.main.components;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.EditText;

import supermarket.main.R;

/**
 * Created by cubesschool3 on 9/7/16.
 */
public class CustomEditText extends EditText {




    public CustomEditText(Context context) {
        super(context);
        setBackgroundDrawable(null);
        setSingleLine(true);
        setCursorVisible(false);
        setTextColor(getResources().getColor(R.color.textColor));
        setHintTextColor(getResources().getColor(R.color.hintColor));

    }

    public CustomEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        setBackgroundDrawable(null);
        setSingleLine(true);
        setCursorVisible(false);
        setTextColor(getResources().getColor(R.color.textColor));
        setHintTextColor(getResources().getColor(R.color.hintColor));
    }

    public CustomEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setBackgroundDrawable(null);
        setSingleLine(true);
        setCursorVisible(false);
        setTextColor(getResources().getColor(R.color.textColor));
        setHintTextColor(getResources().getColor(R.color.hintColor));

    }


}
