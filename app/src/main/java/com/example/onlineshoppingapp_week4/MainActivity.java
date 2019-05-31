package com.example.onlineshoppingapp_week4;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.onlineshoppingapp_week4.Adapter.ViewpagerAdapter;
import com.example.onlineshoppingapp_week4.Fragment.Loginfrag;
import com.example.onlineshoppingapp_week4.Fragment.SignupFrag;

public class MainActivity extends AppCompatActivity {
    private ViewPager viewPager;
    private TabLayout tabLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tabLayout=findViewById(R.id.Tablayout);
        viewPager=findViewById(R.id.viewpager);

        ViewpagerAdapter adapter=new ViewpagerAdapter(getSupportFragmentManager());


        adapter.addFragment(new Loginfrag(),"Login");
        adapter.addFragment(new SignupFrag(),"Signup");

        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

    }


}
