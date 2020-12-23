package com.example.homeserviceapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.example.homeserviceapp.Model.Order;
import com.example.homeserviceapp.Model.ServiceProvider;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

public class ServiceproviderList extends AppCompatActivity {
    TextView service_provider_name, service_provider_rating, service_provider_Experience, msgview;
    ImageView img;
    Button btn, map;
    DatabaseReference databaseReference;
    ServiceProvider sp;
    String keys, status;
    public String userid = null;
    String service_name;
    SharedPreferences orders;
    HashMap<String, String> hashmap = new HashMap<String, String>();


    public void generateData() {

        hashmap.put("AC Repair", "500");
        hashmap.put("General", "200");
        hashmap.put("IPS Installtion and Repair", "400");
        hashmap.put("FAN Repair", "300");
        hashmap.put("Freeze Repair", "400");
        hashmap.put("TV Repair", "500");
        hashmap.put("Secuity cam installtion", "300");
        hashmap.put("physics", "7000");
        hashmap.put("math", "6000");
        hashmap.put("economics", "4000");
        hashmap.put("chemistry", "4000");
        hashmap.put("islamic history", "4000");
        hashmap.put("bangla", "4000");
        hashmap.put("Water Meter Servicing", "300");
        hashmap.put("Water Tap Servicing" ,"500");
        hashmap.put("Kitchen Sink Services", "400");
        hashmap.put("Wash Basin Services", "400");
        hashmap.put("Plumbing CheckUp", "400");


    }

    public String getMoney(String data) {
        return hashmap.get(data).toString();
    }

    public String calculateMoney(String allServices) {
        String s[] = allServices.split("_");
        int total = 0;
        String result;
        for (int i = 0; i < s.length; i++) {
            // Log.i(" vv4",result);
            result = getMoney(s[i]);
            Log.i(" vv3", result);
            total = total + Integer.parseInt(result);


        }
        return total + "";
    }
    //String userLocation = MainActivity.getLoc();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_serviceprovider_list);
        generateData();
        databaseReference = FirebaseDatabase.getInstance().getReference("Electrician");

        //service_name=in.getStringExtra("serviceId");


        service_provider_Experience = (TextView) findViewById(R.id.serviceman_experience_textview_id);
        msgview = (TextView) findViewById(R.id.msg_view_id);
        service_provider_name = (TextView) findViewById(R.id.serviceman_name_textview_id);
        service_provider_rating = (TextView) findViewById(R.id.serviceman_rating_textview_id);
        btn = (Button) findViewById(R.id.confirm_service_provider_id);
        map = (Button) findViewById(R.id.map_location_id);
        img = (ImageView) findViewById(R.id.service_provider_picture_id);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //  openDialogue();
                orderPlace();
                Intent intent = new Intent(getApplicationContext(), ServiceList.class);
                startActivity(intent);
              //  finish();

            }
        });
        map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Intent intent=new Intent(getApplicationContext(),ServiceList.class);
                // startActivity(intent);


                alertDialogue();
            }
        });

    }

    protected void onStart() {

        SharedPreferences usershared = getSharedPreferences("userdetails", Context.MODE_PRIVATE);
        SharedPreferences orders = getSharedPreferences("electricalOrder", Context.MODE_PRIVATE);

        service_name = orders.getString("serviceName", "");
        userid = usershared.getString("userid", "");
        String userloc = usershared.getString("userloc", "");

        String lastLoc;

        String partsIndatabase[] = userloc.split("_");
        lastLoc = partsIndatabase[partsIndatabase.length - 1];
        // Toast.makeText(getApplicationContext(), lastLoc, Toast.LENGTH_SHORT).show();

        // Toast.makeText(this, service_name+" ", Toast.LENGTH_LONG).show();
        databaseReference.orderByChild("location").equalTo(lastLoc).addValueEventListener(new ValueEventListener() {
            // databaseReference.addValueEventListener(new ValueEventListener() {


            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot d : dataSnapshot.getChildren()) {
                    keys = d.getKey();
                    sp = d.getValue(ServiceProvider.class);

                    status = sp.getStatus();
                  //  service_provider_name.setVisibility(View.INVISIBLE);
                    //Toast.makeText(getApplicationContext(), sp.getServices(), Toast.LENGTH_SHORT).show();
                    if (status.equals("1") && service_name.equalsIgnoreCase(sp.getServices())) {

                        service_provider_rating.setText("Rating :" + sp.getRating());
                        service_provider_name.setText("Name :" + sp.getName());
                        service_provider_Experience.setText("Experience :" + sp.getExperience());
                        Picasso.get().load(sp.getImage().toString()).into(img);
                        break;
                    }

                }

            }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        //  Toast.makeText(getApplicationContext(), "finish", Toast.LENGTH_SHORT).show();

        super.onStart();

    }

    public void orderPlace() {

        SharedPreferences orders = getSharedPreferences("electricalOrder", Context.MODE_PRIVATE);

        service_name = orders.getString("serviceName", "");
        Log.i(keys, "y11");
        String providerid = keys;
        Log.i(status, "y11");
        String stat = status;


        //  String currentTime = new SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(new Date());
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy.MM.dd G 'at' HH:mm:ss z");
        String currentDateandTime = sdf1.format(new Date());
        String ordertime = currentDateandTime;
        DatabaseReference databaseReference1 = FirebaseDatabase.getInstance().getReference("Order");
        String key = databaseReference1.push().getKey();
        String name = "";


        Log.i(service_name, "hshshhsh");
        Order order = new Order(userid, providerid, ordertime, "-1", service_name, "200", "0", key, "1", "-1");
        databaseReference1.child(key).setValue(order);

    }

    public void alertDialogue() {
        alert alrt = new alert();
        alrt.show(getSupportFragmentManager(), "aaa");

    }

}
