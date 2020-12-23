package com.example.homeserviceapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

public class NavigationUI extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    DrawerLayout dr;
    ActionBarDrawerToggle Adt;
    TextView tv;
    View inflatedView;
    LayoutInflater inflater;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_ui);
        inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        Intent intent = new Intent(this, ListenOrder.class);
        startService(intent);

        inflatedView = inflater.inflate(R.layout.nav_header, null);
        // inflatedView = getLayoutInflater().inflate(R.layout.nav_header, null);
        tv = (TextView) inflatedView.findViewById(R.id.clicakableText);
        dr = findViewById(R.id.drawerid);

        SharedPreferences usershared = getSharedPreferences("userdetails", Context.MODE_PRIVATE);
       // tv.setTextSize(50);
        //tv.setText("Fdsa");
        tv.setText(" aaaa");
        // Toast.makeText(this,"what: " +tv.getText().toString(), Toast.LENGTH_LONG).show();


        //tv.setText( usershared.getString("username","Gf"));

        NavigationView navigationView = findViewById(R.id.navigationid);
        navigationView.setNavigationItemSelectedListener(this);
        //NavigationView nv=findViewById(R.id.navigationid);
        // nv.setNavigationItemSelectedListener(this);
        Adt = new ActionBarDrawerToggle(this, dr, R.string.nav_open, R.string.nav_close);
        dr.addDrawerListener(Adt);
        Adt.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Fragment fragment = new ServiceList();
        if (fragment != null) {
            FragmentManager fragmentmanager = getSupportFragmentManager();
            FragmentTransaction ft = fragmentmanager.beginTransaction();
            ft.replace(R.id.frameID, fragment);
            ft.commit();

        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (Adt.onOptionsItemSelected(item))
            return true;

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigateUp() {
        return super.onNavigateUp();
    }

    public void Clicked(View v) {
        Intent in = new Intent(getApplicationContext(), profileActivity.class);
        startActivity(in);

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        Fragment fragment = null;

        if (menuItem.getItemId() == R.id.cust_supp_id) {
            startActivity(new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", "01994607759", null)));
        }
        if(menuItem.getItemId() == R.id.settingsid)
        {
            Intent in = new Intent(getApplicationContext(), profileActivity.class);
            startActivity(in);
        }
        if (menuItem.getItemId() == R.id.PreviousOrders) {
            Intent in = new Intent(getApplicationContext(), MyOrders.class);
            startActivity(in);
        }
        if (menuItem.getItemId() == R.id.CurrentOrders) {
            Intent in = new Intent(getApplicationContext(), CurrentOrder.class);
            startActivity(in);
        }
        if (menuItem.getItemId() == R.id.signoutMenu) {
            SharedPreferences spreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
            //  spreferences = getSharedPreferences("userdetails", Context.MODE_PRIVATE);
            spreferences = getSharedPreferences("userdetails", getApplicationContext().MODE_PRIVATE);
            SharedPreferences.Editor spreferencesEditor = spreferences.edit();
            spreferencesEditor.clear();
            spreferencesEditor.commit();

            Intent in = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(in);
        }
        if (fragment != null) {
            FragmentManager fragmentmanager = getSupportFragmentManager();
            FragmentTransaction ft = fragmentmanager.beginTransaction();
            ft.replace(R.id.frameID, fragment);
            ft.commit();

        }

        return false;
    }
}
