package com.example.serverhomeservice;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;

public class NavigationUI extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    DrawerLayout dr;
    ActionBarDrawerToggle Adt;
    TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_ui);

        dr=findViewById(R.id.drawerid);
        tv=(TextView) findViewById(R.id.clicakableText) ;
        //tv.setOnClickListener(new \);


        NavigationView navigationView=findViewById(R.id.navigationid);
        navigationView.setNavigationItemSelectedListener(this);
        //NavigationView nv=findViewById(R.id.navigationid);
       // nv.setNavigationItemSelectedListener(this);
        Adt=new ActionBarDrawerToggle(this,dr,R.string.nav_open,R.string.nav_close);
        dr.addDrawerListener(Adt);
        Adt.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(Adt.onOptionsItemSelected(item))
            return  true;

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigateUp() {
        return super.onNavigateUp();
    }
    public void Clicked(View v)
    {
        Intent in=new Intent(getApplicationContext(),profileActivity.class);
        startActivity(in);


    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        if(menuItem.getItemId()==R.id.homeid)
        {
            Intent in=new Intent(getApplicationContext(),HomepageActivity.class);
            startActivity(in);
        }
        if(menuItem.getItemId()==R.id.cust_supp_id)
        {
            startActivity(new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", "01994607759", null)));
        }

        return false;
    }
}
