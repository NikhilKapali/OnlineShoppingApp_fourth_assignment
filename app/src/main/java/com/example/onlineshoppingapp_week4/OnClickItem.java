package com.example.onlineshoppingapp_week4;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class OnClickItem extends AppCompatActivity {
    ImageView img;
    TextView txtname,txtprice,txtdesc;
    Bundle bundle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_click_item);
        bundle=getIntent().getExtras();
        Individualitem();
    }
    private void Individualitem(){
        img=findViewById(R.id.ivitmimage);
        txtname=findViewById(R.id.itmname);
        txtprice=findViewById(R.id.itmprice);
        txtdesc=findViewById(R.id.itmdesc);

        if(bundle !=null)
        {
            txtname.setText(bundle.getString("itemName"));
            txtprice.setText(bundle.getString("itemPrice"));
            txtdesc.setText(bundle.getString("itemDescription"));
            String image=bundle.getString("itemImageName");

            Picasso.with(this).load(image).into(img);
        }
    }
}
