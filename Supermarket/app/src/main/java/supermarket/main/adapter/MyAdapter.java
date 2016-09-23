package supermarket.main.adapter;

import android.content.Context;
import android.provider.ContactsContract;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import supermarket.main.R;
import supermarket.main.components.CustomTextView;
import supermarket.main.data.DataProduct;

/**
 * Created by Alen on 21.9.2016..
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private ArrayList<DataProduct> mDataset;
    private Context mContext;
    private final OnItemClickListener mListener;


    public class MyViewHolder extends RecyclerView.ViewHolder  {
        public CustomTextView description, price;
        public ImageView picture, basket;
        public RelativeLayout root;

        public MyViewHolder(View view) {
            super(view);
            description = (CustomTextView) view.findViewById(R.id.textViewDescription);
            price = (CustomTextView) view.findViewById(R.id.textViewPrice);
            picture=(ImageView)view.findViewById(R.id.imageViewProduct);
            basket=(ImageView)view.findViewById(R.id.imageViewBasket);
            root=(RelativeLayout)view.findViewById(R.id.product_root);

        }


        public void bind(final DataProduct itemClicked, final OnItemClickListener listener, final View view) {

            view.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    listener.onItemClick(itemClicked);
                    Toast.makeText(mContext,"Image",Toast.LENGTH_SHORT).show();
                }
            });
            }

        public void bakset(final DataProduct itemClicked, final OnItemClickListener listener, final View view) {

            view.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    listener.onItemClick(itemClicked);
                    Toast.makeText(mContext,"Basket",Toast.LENGTH_SHORT).show();
                }
            });
        }
    }


    public MyAdapter(Context context, ArrayList<DataProduct> myDataset,OnItemClickListener listner) {
        mDataset = myDataset;
        mContext=context;
        mListener=listner;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent,
                                           int viewType) {
        // create a new view
        View v = LayoutInflater.from(mContext)
                .inflate(R.layout.product_item, parent, false);

        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
      DataProduct product= mDataset.get(position);
        holder.description.setText(product.name);
        holder.price.setText(product.price);
        Glide.with(mContext)
                .load(product.thumb330).fitCenter()
                .into(holder.picture);
        holder.bind(mDataset.get(position), mListener, holder.picture);
        holder.bakset(mDataset.get(position), mListener, holder.basket);


    }
    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }







}
