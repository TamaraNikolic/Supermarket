package supermarket.main.networking;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.google.gson.Gson;

import java.io.UnsupportedEncodingException;

/**
 * Created by cubesschool3 on 9/12/16.
 */
public class GsonRequest<T> extends Request<T> {

    private final int TIMEOUT=10000;

    private Gson mGson=new Gson();
    private Class<T>mClass;
    private Response.Listener<T>mListenere;
    private Response.ErrorListener mErrorListner;

    public GsonRequest(String url, Response.ErrorListener listener) {
        super(url, listener);
    }
    public GsonRequest(String url,int type,Class<T> myClass,Response.Listener listnere,Response.ErrorListener errorListener){
        super(type,url,errorListener);

        this.mClass=myClass;
        this.mListenere=listnere;
        this.mErrorListner=errorListener;

        setRetryPolicy(new DefaultRetryPolicy(TIMEOUT,0,DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        //posle koliko vremene da se ceka ako se ne javi response da se pojavi error(5sec), koliko puta da se ponovi request pre nego sto se dobije error kao izlaz
    }

    @Override
    protected Response<T> parseNetworkResponse(NetworkResponse response) {
        try {
            String json=new String(response.data, HttpHeaderParser.parseCharset(response.headers));//daje samo string json

            return Response.success(mGson.fromJson(json, mClass),HttpHeaderParser.parseCacheHeaders(response));//

        } catch (UnsupportedEncodingException e) {
            return Response.error(new ParseError(e));
        }

    }

    @Override
    protected void deliverResponse(T response) {

    mListenere.onResponse(response);

    }

    @Override
    protected VolleyError parseNetworkError(VolleyError volleyError) {
        return super.parseNetworkError(volleyError);
    }
}
