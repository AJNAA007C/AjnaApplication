package com.example.roufal.ajnaapplication.view.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.roufal.ajnaapplication.R;
import com.example.roufal.ajnaapplication.Service.model.album;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;


public class AlbumAdapter extends RecyclerView.Adapter<AlbumAdapter.MyViewHolder> {
    private Context mContext;
    List<album> itemList = new ArrayList<album>();


    /*public GridView1(Context c, List<Business> image_list) {
         mContext = c;
         this.itemList = image_list;
     }*/
    public AlbumAdapter(Context c, List<album> list) {
        this.mContext = c;
        itemList = list;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.image_raw, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
       try {
           holder.title.setText(itemList.get(position).getTitle());
           Picasso.with(mContext).load(itemList.get(position).getThumbnailUrl()).into(holder.image);
           Picasso.with(mContext).load(itemList.get(position).getUrl()).into(holder.imageView2);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public int getItemCount() {
        try {
            return itemList.size();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView title;
        public ImageView image,imageView2;


        public MyViewHolder(View view) {
            super(view);
            title = view.findViewById(R.id.textView);
            image = view.findViewById(R.id.imageView);
            imageView2 = view.findViewById(R.id.imageView3);



        }

    }
    public void removeAt(int position) {
        itemList.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, itemList.size());
        notifyDataSetChanged();
    }

}