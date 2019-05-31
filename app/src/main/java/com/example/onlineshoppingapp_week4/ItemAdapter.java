package com.example.onlineshoppingapp_week4;

import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.StrictMode;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.onlineshoppingapp_week4.Model.ItemModel;
import com.example.onlineshoppingapp_week4.Views.Dashboard;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemViewHolder> {

    List<ItemModel> itemModelList;
    Context context;
    Bitmap bitmap;
    public static final String BASE_URL= "http://10.0.2.2:3000";

    public ItemAdapter(List<ItemModel> itemModelList, Context context) {
        this.itemModelList = itemModelList;
        this.context = context;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.sample_recycler_view,viewGroup,false);
        return new ItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder itemViewHolder, int i) {
        final ItemModel itemModel=itemModelList.get(i);
        itemViewHolder.tv_heading.setText(itemModel.getItemName());


        Picasso.with(context).load(BASE_URL+"/images/"+itemModel.getItemImageName()).into(itemViewHolder.img);
        Log.d("log", "onBindViewHolder: "+BASE_URL+"/images/"+itemModel.getItemImageName());


        Toast.makeText(context, ""+itemModelList.size(), Toast.LENGTH_SHORT).show();



      itemViewHolder.img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context vcontext= v.getContext();
                String send_path=BASE_URL+"images/"+itemModel.getItemImageName();

                System.out.println(send_path);

                Intent intent=new Intent(context, OnClickItem.class);
                intent.putExtra("itemName",itemModel.getItemName());
                intent.putExtra("itemPrice",itemModel.getItemPrice());
                intent.putExtra("itemImageName",BASE_URL+"/images/"+itemModel.getItemImageName());
                intent.putExtra("itemDescription",itemModel.getItemDescription());

                vcontext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return itemModelList.size();
    }


    public class ItemViewHolder extends RecyclerView.ViewHolder {

        public TextView tv_heading;
        public ImageView img;


        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_heading=itemView.findViewById(R.id.sample_heading);
            img=itemView.findViewById(R.id.sample_image);
        }
    }
}




