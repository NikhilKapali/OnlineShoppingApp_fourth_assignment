package com.example.onlineshoppingapp_week4.Views;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.onlineshoppingapp_week4.API.ItemAPI;
import com.example.onlineshoppingapp_week4.API.UserAPI;
import com.example.onlineshoppingapp_week4.Model.ItemModel;
import com.example.onlineshoppingapp_week4.Model.ItemimageModel;
import com.example.onlineshoppingapp_week4.R;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Additem extends AppCompatActivity implements View.OnClickListener {
EditText etitemname,etitemprice,etitemdesc;
    TextView tvitemimgname;
Button btn_select,btn_upload,btn_additem;
ImageView ivitem;
Uri imageuri;
Bitmap bitmap;
Retrofit retrofit;
ItemAPI itemAPI;
private static final int PICK_IMAGE=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_additem);

        etitemname=findViewById(R.id.et_itemname);
        etitemprice=findViewById(R.id.et_itemprice);
        etitemdesc=findViewById(R.id.et_itemdesc);
        tvitemimgname=findViewById(R.id.tv_itemimgname);
        ivitem=findViewById(R.id.iv_item);
        btn_select=findViewById(R.id.btn_selectimg);
        btn_upload=findViewById(R.id.btn_upimg);
        btn_additem=findViewById(R.id.btn_uploaditem);

        btn_select.setOnClickListener(this);
        btn_upload.setOnClickListener(this);
        btn_additem.setOnClickListener(this);
    }

    private void createInstance()
    {
        retrofit=new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:3000/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        itemAPI = retrofit.create(ItemAPI.class);


    }

    @Override
    public void onClick(View v) {
        if (v.getId()==R.id.btn_selectimg)
        {
            Opengallery();
        }
        else if(v.getId()==R.id.btn_upimg)
        {
            additemImage(bitmap);
        }
        else if(v.getId()==R.id.btn_uploaditem)
        {
            uploadItem();
        }
    }
    private void Opengallery()
    {
        Intent gallery=new Intent();
        gallery.setType("image/*");
        gallery.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(gallery,"choose Image to upload"),PICK_IMAGE);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==PICK_IMAGE && resultCode==RESULT_OK){
            imageuri=data.getData();
            try{
                bitmap= MediaStore.Images.Media.getBitmap(getContentResolver(),imageuri);
                ivitem.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void uploadItem() {
        String itemName = etitemname.getText().toString();
        String itemPrice = etitemprice.getText().toString();
        String itemImageName = tvitemimgname.getText().toString();
        String itemImageDescription = etitemdesc.getText().toString();
        createInstance();
        Call<ItemModel> itemuploadCall = itemAPI.insertItem(itemName, itemPrice, itemImageName, itemImageDescription);
        itemuploadCall.enqueue(new Callback<ItemModel>() {
            @Override
            public void onResponse(Call<ItemModel> call, Response<ItemModel> response) {
                Toast.makeText(Additem.this, "Item Added Successfully", Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(Additem.this,Dashboard.class);
                startActivity(intent);

            }

            @Override
            public void onFailure(Call<ItemModel> call, Throwable t) {
                Toast.makeText(Additem.this, "Failure", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void additemImage(Bitmap bitmap)
    {
        ByteArrayOutputStream stream=new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,100,stream);
        byte[] bytes=stream.toByteArray();

        try {
            File file=new File(this.getCacheDir(),"image.jpeg");
            file.createNewFile();
            FileOutputStream fileOutputStream=new FileOutputStream(file);
            fileOutputStream.write(bytes);
            fileOutputStream.flush();
            fileOutputStream.close();
            RequestBody requestBody=RequestBody.create(MediaType.parse("multipart/form-data"),file);
            MultipartBody.Part body=MultipartBody.Part.createFormData("ImageName",file.getName(),requestBody);

            createInstance();
            itemAPI=retrofit.create(ItemAPI.class);
            Call<String> itemimageCall = itemAPI.uploadItemImage(body);
            itemimageCall.enqueue(new Callback<String>() {
                @Override
                public void onResponse(Call<String> call, Response<String> response) {
                    Toast.makeText(Additem.this, response.body(), Toast.LENGTH_SHORT).show();
                   tvitemimgname.setText(response.body());
                }

                @Override
                public void onFailure(Call<String> call, Throwable t) {
                    Toast.makeText(Additem.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }





}


